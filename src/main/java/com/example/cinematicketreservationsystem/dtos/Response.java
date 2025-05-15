package com.example.cinematicketreservationsystem.dtos;

import java.time.LocalDateTime;

public class Response<T> {
    private final LocalDateTime timeStamp;
    private final String message;
    private final T data;

    public Response(LocalDateTime timeStamp, String message, T data) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.data = data;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    private Response(Builder<T> builder) {
        this.timeStamp = builder.timeStamp;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static <T> Builder<T> builder(){
        return new Builder<>();
    }

    public static class Builder<T>{
        private final LocalDateTime timeStamp = LocalDateTime.now();
        private String message;
        private T data;

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Response<T> build(){
            return new Response<>(this);
        }
    }
}
