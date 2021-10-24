package com.sillock.core.advice;

import com.sillock.common.dto.ErrorResponseDto;
import com.sillock.core.error.AccessNotAllowedException;
import com.sillock.core.error.BadRequestException;
import com.sillock.core.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;

/**
 * TODO: error 패키지 내부 어드바이스랑 합치기
 */
@Priority(20)
@RestControllerAdvice
public class DefaultControllerAdvice extends AbstractControllerAdvice {
    @ExceptionHandler(value = { Exception.class})
    public ResponseEntity<ErrorResponseDto<?>> handleUnknownException(Exception e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<ErrorResponseDto<?>> handleBadRequestException(Exception e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponseDto<?>> handleNotFoundException(Exception e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { AccessNotAllowedException.class})
    public ResponseEntity<ErrorResponseDto<?>> handleForbiddenException(Exception e) {
        return handleException(e, HttpStatus.FORBIDDEN);
    }
}
