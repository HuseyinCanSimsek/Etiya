package com.etiya.ecommercedemopair1.core.util.results;

public class SuccessDataResult<T> extends DataResult<T>{
    public SuccessDataResult(String message, T data) {
        super(true,message,data);
    }
    public SuccessDataResult(T data)
    {
        super(data,true);
    }
    public SuccessDataResult(String message)
    {
        super(true,message,null);
    }
}
