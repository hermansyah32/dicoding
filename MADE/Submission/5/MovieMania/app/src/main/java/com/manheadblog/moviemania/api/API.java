package com.manheadblog.moviemania.api;

public class API {
    public static APIRequest getClient() {
        return ClientInstance.getApiInstance().create(APIRequest.class);
    }
}
