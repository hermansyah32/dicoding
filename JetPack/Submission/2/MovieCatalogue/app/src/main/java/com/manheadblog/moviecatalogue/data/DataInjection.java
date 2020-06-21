package com.manheadblog.moviecatalogue.data;

import android.app.Application;

import com.manheadblog.moviecatalogue.data.remote.RemoteRepository;

public class DataInjection {
    public static RepositoryData provideRepository(){
        return RepositoryData.getInstance(RemoteRepository.getInstance());
    }
}
