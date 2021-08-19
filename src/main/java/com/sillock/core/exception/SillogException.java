package com.sillock.core.exception;

/**
 * 실록 도메인 비즈니스 예외 (500 Internal Server Error)
 */
public class SillogException extends RuntimeException{
    public SillogException(){
    }

    public SillogException(String message){
        super(message);
    }

    public SillogException(String message, Throwable cause){
        super(message, cause);
    }
    public SillogException(Throwable cause){
        super(cause);
    }
    public SillogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}