package com.etiya.ecommercedemopair1.core.util.results;

public class SuccessResult extends Result{
    public SuccessResult(boolean success) {
        super(true);
    }
    public SuccessResult(boolean success,String message)
    {
        super(true,message);
    }
    public SuccessResult(String message)
    {
        super(true,message);
    }

}
