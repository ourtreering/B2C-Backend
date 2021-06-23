package com.sillock.core.advice;

import com.sillock.common.dto.ErrorResponseDto;
import com.sillock.common.dto.ResponseDto;
import com.sillock.core.exception.AccessNotAllowedException;
import com.sillock.core.exception.BadRequestException;
import com.sillock.core.exception.ResourceNotFoundException;
import com.sillock.core.exception.SillockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;

/**
 * created by hyunwoo 21/06/23
 */
@Priority(20)
@RestControllerAdvice
public class DefaultControllerAdvice extends AbstractControllerAdvice {
    @ExceptionHandler(value = { Exception.class, SillockException.class })
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
