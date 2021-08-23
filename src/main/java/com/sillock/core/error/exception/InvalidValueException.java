package com.sillock.core.error.exception;

import com.sillock.core.error.ErrorCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }

}