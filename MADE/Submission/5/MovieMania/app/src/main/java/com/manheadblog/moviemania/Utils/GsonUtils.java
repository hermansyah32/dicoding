package com.manheadblog.moviemania.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonUtils {
    public static <T> List<T> getList(String jsonArray, Class<T> clazz) {
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return new Gson().fromJson(jsonArray, typeOfT);
    }

    public static <T> String toJSON(T object) {
        return new Gson().toJson(object);
    }

    public static <T> T parseObject(String jsondata, Class<T> clazz) {
        return new Gson().fromJson(jsondata, clazz);
    }
}
