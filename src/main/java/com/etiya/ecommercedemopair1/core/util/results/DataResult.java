package com.etiya.ecommercedemopair1.core.util.results;

public class DataResult<T> extends Result {
    private T data;


    public DataResult(boolean success, String message,T data) {
        super(success, message);
        this.data=data;
    }
    public DataResult(String message,T data)
    {
        super(message);
        this.data=data;
    }
    public DataResult(T data,boolean success)
    {
        super(success);
        this.data=data;
    }
    public T getData()
    {
        return this.data;
    }
}
