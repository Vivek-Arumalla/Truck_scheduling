package com.cognizant.vendorservice.service;

import com.cognizant.vendorservice.exception.EmailFormatException;
import com.cognizant.vendorservice.exception.MobileNumberFormatException;
import com.cognizant.vendorservice.exception.VendorAlreadyExistsException;
import com.cognizant.vendorservice.exception.VendorNotFoundException;
import com.cognizant.vendorservice.model.VendorDetails;
import com.cognizant.vendorservice.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class VendorService {
    String emailRegEx= "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    String mobileRegEx="(0/91)?[6-9][0-9]{9}";

    @Autowired
    VendorRepository vendorRepository;

    public List<VendorDetails> getVendorDetails() {
        return (List<VendorDetails>) vendorRepository.findAll();
    }

    public void deleteAll() { vendorRepository.deleteAll(); }

    public VendorDetails insertVendorDetails(VendorDetails vendorDetails) {
        if(Pattern.matches(emailRegEx,vendorDetails.getVendorEmail())){
            if(vendorRepository.findByEmail(vendorDetails.getVendorEmail()).isEmpty()){
                if(Pattern.matches(mobileRegEx,vendorDetails.getVendorPhoneNumber()))
                    return vendorRepository.save(vendorDetails);
                else
                    throw new MobileNumberFormatException();
            }
            else
                throw new VendorAlreadyExistsException();
        }
        else
            throw new EmailFormatException();



    }
    public void deleteVendorDetails(int vendorId){
        if(vendorRepository.existsById(vendorId))
            vendorRepository.deleteById(vendorId);
        else
            throw new VendorNotFoundException();
    }

    public VendorDetails updateVendor(VendorDetails vendorDetails, int vendorId) {
        if(vendorRepository.existsById(vendorId)){
            VendorDetails vendorDetails1 = vendorRepository.findById(vendorId).get();
            vendorDetails1.setVendorAddress(vendorDetails.getVendorAddress());
            if(Pattern.matches(emailRegEx,vendorDetails.getVendorEmail()))
                vendorDetails1.setVendorEmail(vendorDetails.getVendorEmail());
            else
                throw new EmailFormatException();
            vendorDetails1.setVendorName(vendorDetails.getVendorName());
            vendorDetails1.setVendorPhoneNumber(vendorDetails.getVendorPhoneNumber());
            try{
                return vendorRepository.save(vendorDetails1);
            }
            catch (Exception e){

                    throw new VendorAlreadyExistsException();
            }

        }
        else
            throw new VendorNotFoundException();
    }

    public VendorDetails getVendorDetailsByName(String vendorName) {
        VendorDetails vendorDetails = vendorRepository.findByVendorName(vendorName);
        if(vendorDetails == null)
            throw new VendorNotFoundException();
        else
            return vendorDetails;
    }

    public VendorDetails getVendorDetailsById(int vendorId) {
        if(vendorRepository.existsById(vendorId)){
            return vendorRepository.findById(vendorId).get();
        }
        throw new VendorNotFoundException();
    }
}
