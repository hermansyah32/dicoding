package com.manheadblog.moviecatalogue.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.manheadblog.moviecatalogue.data.remote.APIResponse;
import com.manheadblog.moviecatalogue.data.resource.Resource;
import com.manheadblog.moviecatalogue.utils.AppExecutors;

public abstract class NetworkBoundResourceFetchPrefer<ResultType, RequestType> {

    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors mExecutors;

    protected void onFetchFailed() {
    }

    protected abstract LiveData<ResultType> loadFromDB();

    protected abstract Boolean shouldUseDBonError(ResultType data);

    protected abstract LiveData<APIResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    public NetworkBoundResourceFetchPrefer(AppExecutors appExecutors) {

        this.mExecutors = appExecutors;
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDB();

        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            fetchFromNetwork(dbSource);
        });

    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {

        LiveData<APIResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, newData ->
                result.setValue(Resource.loading(newData))
        );
        result.addSource(apiResponse, response -> {

            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.status) {
                case SUCCESS:
                    mExecutors.diskIO().execute(() -> {

                        saveCallResult(response.body);

                        mExecutors.mainThread().execute(() ->
                                result.addSource(loadFromDB(),
                                        newData -> result.setValue(Resource.success(newData))));

                    });
                    break;

                case EMPTY:
                    mExecutors.mainThread().execute(() ->
                            result.addSource(loadFromDB(),
                                    newData -> result.setValue(Resource.success(newData))));

                    break;
                case ERROR:
                    onFetchFailed();
                    mExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            result.addSource(dbSource, data -> {
                                result.removeSource(dbSource);
                                if (shouldUseDBonError(data)){
                                    result.addSource(dbSource, newData -> result.setValue(Resource.successDB(newData)));
                                }else {
                                    result.addSource(dbSource, newData ->
                                            result.setValue(Resource.error(response.status_message, newData)));
                                }

                            });
                        }
                    });
                    break;
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}

