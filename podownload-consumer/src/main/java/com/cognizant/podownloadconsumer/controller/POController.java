package com.cognizant.podownloadconsumer.controller;

import com.cognizant.podownloadconsumer.model.POInformation;
import com.cognizant.podownloadconsumer.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/podetails")
public class POController {

    @Autowired
    ReceiverService receiverService;

    @GetMapping("/{poNumber}")
    public ResponseEntity<POInformation> getByTruckNumber(@PathVariable(value = "poNumber") int poNumber){
        return ResponseEntity.ok(receiverService.getByPONumber(poNumber));
    }
    @DeleteMapping
    public void deleteAll(){
        receiverService.deleteAll();
    }
}
