package com.manheadblog.moviecatalogue.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.manheadblog.moviecatalogue.data.remote.StatusResponse.EMPTY;
import static com.manheadblog.moviecatalogue.data.remote.StatusResponse.ERROR;
import static com.manheadblog.moviecatalogue.data.remote.StatusResponse.SUCCESS;

public class APIResponse<T> {

    @NonNull
    public final StatusResponse status;

    @NonNull
    public String status_message;

    @NonNull
    public int status_code;

    @Nullable
    public final T body;

    public APIResponse(@NonNull StatusResponse status, @NonNull String status_message, int status_code, @Nullable T body) {
        this.status = status;
        this.status_message = status_message;
        this.status_code = status_code;
        this.body = body;
    }

    public static <T> APIResponse<T> success(@Nullable T body){
        return new APIResponse<>(SUCCESS, "Berhasil", 200, body);
    }

    public static <T> APIResponse<T> empty(@Nullable T body){
        return new APIResponse<>(EMPTY, "Data Kosong", 200, body);
    }

    public static <T> APIResponse<T> error(String message, int code, @Nullable T body){
        return new APIResponse<>(ERROR, message, code, body);
    }
}
