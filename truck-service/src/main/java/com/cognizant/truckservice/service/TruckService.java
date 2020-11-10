package com.cognizant.truckservice.service;

import com.cognizant.truckservice.exception.TruckAlreadyExistsException;
import com.cognizant.truckservice.exception.TruckNotFoundException;
import com.cognizant.truckservice.exception.TruckNumberFormatException;
import com.cognizant.truckservice.model.Truck;
import com.cognizant.truckservice.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class TruckService {
    String regEx = "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";

    @Autowired
    TruckRepository truckRepository;

    public List<Truck> getAllTruckDetails(){

        return (List<Truck>) truckRepository.findAll();
    }
    public void deleteAll(){ truckRepository.deleteAll(); }

    public Truck registerTruck(Truck truck){
        if(truckRepository.findByTruckNumber(truck.getTruckNumber())==null){
            if(Pattern.matches(regEx,truck.getTruckNumber()))
                return truckRepository.save(truck);
            else
                throw new TruckNumberFormatException();
        }
        else
            throw new TruckAlreadyExistsException();

    }

    public void deleteTruck(int truckId){
        if(truckRepository.existsById(truckId))
            truckRepository.deleteById(truckId);
        else
            throw new TruckNotFoundException();

    }

    public Truck getByTruckNumber(String truckNumber){
        if(truckRepository.findByTruckNumber(truckNumber)!=null)
            return truckRepository.findByTruckNumber(truckNumber);
        else
            throw new TruckNotFoundException();
    }

    public Truck updateTruckDetails(Truck truck,int truckId){
        if(truckRepository.existsById(truckId)){
            Truck truck1 = truckRepository.findById(truckId).get();
            if(Pattern.matches(regEx,truck.getTruckNumber()))
                truck1.setTruckNumber(truck.getTruckNumber());
            else
                throw  new TruckNumberFormatException();
            truck1.setTruckName(truck.getTruckName());
            truck1.setTruckType(truck.getTruckType());
            try {
                return truckRepository.save(truck1);
            }
            catch (Exception e) {
                throw new TruckAlreadyExistsException();
            }
        }
        else
            throw new TruckNotFoundException();
    }

}
