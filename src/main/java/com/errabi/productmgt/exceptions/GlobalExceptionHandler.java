package com.errabi.productmgt.exceptions;

import com.errabi.productmgt.web.dtos.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.errabi.productmgt.utils.ProductConstant.SYSTEM_ERROR;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseInfo> handleAllExceptions(Exception ex) {
        ResponseInfo response = ResponseInfo.builder()
                .errorCode(SYSTEM_ERROR)
                .errorDescription(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseInfo> handleNotFoundException(EntityNotFoundException ex) {
        ResponseInfo response = ResponseInfo.builder()
                .errorCode(SYSTEM_ERROR)
                .errorDescription(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameValueRequiredException.class)
    public ResponseEntity<ResponseInfo> handleNameValueRequiredException(NameValueRequiredException ex) {
        ResponseInfo response = ResponseInfo.builder()
                .errorCode(SYSTEM_ERROR)
                .errorDescription(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
