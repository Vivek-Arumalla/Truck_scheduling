package com.cognizant.dcservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DcExceptionContoller {
    @ExceptionHandler(value = DcNotFoundException.class)
    public ResponseEntity<Object> exception(DcNotFoundException exception){
        return  new ResponseEntity<>("Dc Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = DcAlreadyExistsException.class)
    public ResponseEntity<Object> exception(DcAlreadyExistsException exception){
        return new ResponseEntity<>("Dc Already Exists / Dc With given City name exists.",HttpStatus.FOUND);
    }

}
