package com.cognizant.dcslotservice.service;


import com.cognizant.dcslotservice.exception.DcNotFoundException;
import com.cognizant.dcslotservice.exception.DcSlotNotFoundException;
import com.cognizant.dcslotservice.exception.TimeSlotFormatException;
import com.cognizant.dcslotservice.model.DC;
import com.cognizant.dcslotservice.model.DcSlots;
import com.cognizant.dcslotservice.repository.DcSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DcSlotsService {

    String dcUrl = "http://localhost:8083/dc-service/api/v1/dc/";

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    DcSlotsRepository dcSlotsRepository;

     public List<DcSlots> getDcSlotsDetails() {
        return (List<DcSlots>) dcSlotsRepository.findAll();
    }

    public void deleteAll(){ dcSlotsRepository.deleteAll(); }

    public DcSlots insertDcSlotDetails(DcSlots dcSlots){
        dcSlots.setDc(getDcByCityName(dcSlots.getDc().getDcCity()));
        dcSlots.setTimeSlot(timeSlot(dcSlots.getTimeSlot()));
        return dcSlotsRepository.save(dcSlots);
     }

    public List<DcSlots> getDcSlotsDetailsByDcNumber(int dcNumber) {

        if(dcSlotsRepository.findByDc(getDcById(dcNumber)).isEmpty()) {
            throw new DcSlotNotFoundException();
        }
        return (List<DcSlots>) dcSlotsRepository.findByDc(getDcById(dcNumber));
    }

    public void deleteDcSlot(int slotNumber) {
        if(dcSlotsRepository.findById(slotNumber).isPresent())
            dcSlotsRepository.deleteById(slotNumber);
        else
            throw new DcSlotNotFoundException();
    }

    public DcSlots updateDcSlotDetails(DcSlots dcSlots, int slotNumber) {

       if(dcSlotsRepository.findById(slotNumber).isPresent()){
           DC dc = getDcByCityName(dcSlots.getDc().getDcCity());
           System.out.println(dc);
           if(dc != null) {
               DcSlots dcSlotDetails = dcSlotsRepository.findById(slotNumber).get();
               dcSlotDetails.setDc(dc);
               dcSlotDetails.setMaxTrucks(dcSlots.getMaxTrucks());
               dcSlotDetails.setTimeSlot(timeSlot(dcSlots.getTimeSlot()));
               return dcSlotsRepository.save(dcSlotDetails);
           }
           else
               throw new DcSlotNotFoundException();
       }
       else
           throw new DcNotFoundException();

    }

    public String timeSlot(String timeSlot){
         int startTime = Integer.parseInt(timeSlot);
         int endTime=0;
         if(startTime>23) {
             if(startTime==24) {
                 startTime=0;
             } else
                 throw new TimeSlotFormatException();
         }
         endTime = startTime+1;
         return String.format("%d.00 to %d.00",startTime,endTime);

    }

    public DC getDcByCityName(String dcCityName)
    {
        String dcCityNameUrl = dcUrl +"name/" + dcCityName;
        try{
            return restTemplate.getForObject(dcCityNameUrl, DC.class);
        }
        catch(Exception e){
            throw new DcNotFoundException();
        }
    }

    public DC getDcById(int dcNumber)
    {
        String dcNumberUrl = dcUrl +"id/" + dcNumber;
        DC resultDc = restTemplate.getForObject(dcNumberUrl, DC.class);
        return resultDc;

    }

    public DcSlots getDcSlotsDetailsBySlotNumber(int slotNumber) {
        if(dcSlotsRepository.findById(slotNumber).isPresent()){
            return dcSlotsRepository.findById(slotNumber).get();
        }
        throw new DcSlotNotFoundException();
    }
}
