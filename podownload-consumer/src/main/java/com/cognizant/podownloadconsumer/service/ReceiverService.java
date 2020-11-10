package com.cognizant.podownloadconsumer.service;

import com.cognizant.podownloadconsumer.model.POInformation;
import com.cognizant.podownloadconsumer.repository.PORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {


    @Autowired
    PORepository poRepository;

    @StreamListener(Processor.INPUT)
    public void registerPO(POInformation poInformation){
        poRepository.save(poInformation);
    }

    public POInformation getByPONumber(int poNumber) {
        return poRepository.findById(poNumber).get();
    }

    public void deleteAll() {
        poRepository.deleteAll();
    }
}
