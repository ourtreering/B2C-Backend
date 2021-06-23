package com.sillock.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * created by hyunwoo 21/06/23
 */
@Getter
public class ErrorResponseDto<T> {
    private final String message;
    private final long timestamp;

    public ErrorResponseDto(String message){
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ErrorResponseDto<T> of(String message){
        return new ErrorResponseDto<>(message);
    }

}
