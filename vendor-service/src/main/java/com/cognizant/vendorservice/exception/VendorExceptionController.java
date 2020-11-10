package com.cognizant.vendorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VendorExceptionController {
    @ExceptionHandler(value = VendorAlreadyExistsException.class)
    public ResponseEntity<Object> exception(VendorAlreadyExistsException exception){
        return  new ResponseEntity<>("Vendor Already Exists / Vendor with same Email Exists", HttpStatus.FOUND);
    }
    @ExceptionHandler(value = VendorNotFoundException.class)
    public ResponseEntity<Object> exception(VendorNotFoundException exception){
        return  new ResponseEntity<>("Vendor Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EmailFormatException.class)
    public ResponseEntity<Object> exception(EmailFormatException exception){
        return new ResponseEntity<>("Please check Email, It should be in 'abc@*****.***/**'", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = MobileNumberFormatException.class)
    public ResponseEntity<Object> exception(MobileNumberFormatException exception){
        return new ResponseEntity<>("Invalid Mobile Number. Format: '9876543210",HttpStatus.NOT_ACCEPTABLE);
    }

}
