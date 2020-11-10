package com.cognizant.truckservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TruckExceptionController {
    @ExceptionHandler(value = TruckNotFoundException.class)
    public ResponseEntity<Object> exception(TruckNotFoundException exception){
        return  new ResponseEntity<>("Truck Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = TruckAlreadyExistsException.class)
    public ResponseEntity<Object> exception(TruckAlreadyExistsException exception){
        return new ResponseEntity<>("Truck Already Exists / Truck With same Number Exists",HttpStatus.FOUND);
    }
    @ExceptionHandler(value = TruckNumberFormatException.class)
    public ResponseEntity<Object> exception(TruckNumberFormatException exception){
        return  new ResponseEntity<>("Truck Number should be in the format 'AB 12 CD 3456'",HttpStatus.NOT_ACCEPTABLE);
    }
}
