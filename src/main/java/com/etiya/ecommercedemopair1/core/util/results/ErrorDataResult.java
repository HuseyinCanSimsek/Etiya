package com.etiya.ecommercedemopair1.core.util.results;

public class ErrorDataResult<T> extends DataResult<T>{
    public ErrorDataResult(String message, T data) {
        super(false,message,data);
    }
    public ErrorDataResult(T data)
    {
        super(data,false);
    }
    public ErrorDataResult(String message)
    {
        super(false,message,null);
    }

}
