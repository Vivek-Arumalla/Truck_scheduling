package com.cognizant.podownloadproducer.controller;

import com.cognizant.podownloadproducer.model.POInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sender/v1")
public class ProducerController {
    @Autowired
    private MessageChannel output;

    @PostMapping
    public void registerPO(@RequestBody POInformation poInformation){

        Message<POInformation> message = MessageBuilder.withPayload(poInformation).build();
        output.send(message);
    }
}
