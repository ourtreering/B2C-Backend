package com.sillock.core.exception;

/**
 * 실록 도메인 비즈니스 예외 (500 Internal Server Error)
 */
public class SillockException extends RuntimeException{
    public SillockException(){
    }

    public SillockException(String message){
        super(message);
    }

    public SillockException(String message, Throwable cause){
        super(message, cause);
    }
    public SillockException(Throwable cause){
        super(cause);
    }
    public SillockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}