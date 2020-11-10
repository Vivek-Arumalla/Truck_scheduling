package com.cognizant.dcservice.service;

import com.cognizant.dcservice.exception.DcAlreadyExistsException;
import com.cognizant.dcservice.exception.DcNotFoundException;
import com.cognizant.dcservice.model.DC;
import com.cognizant.dcservice.repository.DcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DCService {

    @Autowired
    DcRepository dcRepository;

    public List<DC> getDcDetails() {
        return (List<DC>) dcRepository.findAll();
    }

    public void deleteAll(){ dcRepository.deleteAll(); }

    public DC getDcByNumber(int dcNumber)  {
        if(dcRepository.findById(dcNumber).isPresent()){
            return dcRepository.findById(dcNumber).get();
        }
        else
            throw new DcNotFoundException();
    }

    public DC insertDcDetails(DC dc){
        if(dcRepository.findByDcCity(dc.getDcCity()).isPresent()){
            throw new DcAlreadyExistsException();
        }
        return dcRepository.save(dc);
    }

    public void deleteDc(int dcNumber) {
        if(dcRepository.findById(dcNumber).isPresent())
            dcRepository.deleteById(dcNumber);
        else
            throw new DcNotFoundException();
    }

    public DC updateDcDetails(DC dc, int dcNumber)  {
        if(dcRepository.findById(dcNumber).isPresent()) {
            DC dcDetails = dcRepository.findById(dcNumber).get();
            dcDetails.setDcCity(dc.getDcCity());
            dcDetails.setDcType(dc.getDcType());
            try{
                return dcRepository.save(dcDetails);
            }
            catch (Exception e){
                throw new DcAlreadyExistsException();
            }
        }
        throw new DcNotFoundException();
    }

    public DC getDcByCityName(String dcCityName) {
        if(dcRepository.findByDcCity(dcCityName).isPresent()){
            return dcRepository.findByDcCity(dcCityName).get();
        }
        else
            throw  new DcNotFoundException();
    }
}
