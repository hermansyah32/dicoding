package com.manheadblog.moviecatalogue.data.resource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.manheadblog.moviecatalogue.data.resource.Status.ERROR;
import static com.manheadblog.moviecatalogue.data.resource.Status.LOADING;
import static com.manheadblog.moviecatalogue.data.resource.Status.SUCCESS;
import static com.manheadblog.moviecatalogue.data.resource.Status.SUCCESS_DB;

public class Resource<T> {
    @NonNull
    public final Status status;

    @NonNull
    public final String message;

    @NonNull
    public final T data;

    public Resource(@NonNull Status status, @NonNull String message, @NonNull T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Resource<T> success (@NonNull T data) {
        return new Resource<>(SUCCESS, null, data);
    }

    public static <T> Resource<T> successDB (@NonNull T data) {
        return new Resource<>(SUCCESS_DB, null, data);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, msg, data);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, null, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
