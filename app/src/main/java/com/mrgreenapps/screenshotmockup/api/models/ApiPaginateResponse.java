package com.mrgreenapps.screenshotmockup.api.models;

public class ApiPaginateResponse<T> {
    public T data;
    public int to;
    public int total;
    public int current_page;

    public ApiPaginateResponse(T data, int to, int total, int current_page) {
        this.data = data;
        this.to = to;
        this.total = total;
        this.current_page = current_page;
    }
}
