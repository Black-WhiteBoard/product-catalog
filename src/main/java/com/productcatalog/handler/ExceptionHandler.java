package com.productcatalog.handler;

import com.productcatalog.exception.ApplicationException;
import com.productcatalog.exception.DataNotFoundException;
import com.productcatalog.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(DataNotFoundException dataNotFoundException){
        ErrorResponse errorResponse= new ErrorResponse("DATA_NOT_FOUND","no data found : "+dataNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationError(ApplicationException applicationException){
        ErrorResponse errorResponse= new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),"Internal Server Error,Please try again later");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
