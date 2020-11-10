package com.cognizant.dcslotservice.exception;

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

    @ExceptionHandler(value = DcSlotNotFoundException.class)
    public ResponseEntity<Object> exception(DcSlotNotFoundException exception){
        return new ResponseEntity<>("Dc Slot Not Found.",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = DcTimeSlotUnavailableException.class)
    public ResponseEntity<Object> exception(DcTimeSlotUnavailableException exception){
        return new ResponseEntity<>("Dc Slot is Unavailable.",HttpStatus.FOUND);
    }
    @ExceptionHandler(value = TimeSlotFormatException.class)
    public ResponseEntity<Object> exception(TimeSlotFormatException exception){
        return new ResponseEntity<>("Time Slot should be in the format 'HH'.",HttpStatus.NOT_ACCEPTABLE);
    }
}
