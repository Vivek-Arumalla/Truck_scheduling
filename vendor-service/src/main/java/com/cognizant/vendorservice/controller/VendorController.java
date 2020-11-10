package com.cognizant.vendorservice.controller;

import com.cognizant.vendorservice.model.VendorDetails;
import com.cognizant.vendorservice.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @GetMapping
    public ResponseEntity<List<VendorDetails>> getVendorDetails(){
        return ResponseEntity.ok(vendorService.getVendorDetails());
    }

    @PostMapping
    public ResponseEntity<?> insertVendorDetails(@Valid @RequestBody VendorDetails vendorDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.insertVendorDetails(vendorDetails));
    }

    @DeleteMapping("/{vendorId}")
    public ResponseEntity<?> deleteVendor(@PathVariable(value = "vendorId") int vendorId){
        vendorService.deleteVendorDetails(vendorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void deleteAll() {
        vendorService.deleteAll();
    }

    @GetMapping("/id/{vendorId}")
    public VendorDetails getVendorDetailsById(@PathVariable(value = "vendorId") int vendorId){
        return vendorService.getVendorDetailsById(vendorId);
    }
    @GetMapping("/{vendorName}")
    public VendorDetails getVendorDetailsByName(@PathVariable(value = "vendorName") String vendorName){
        return vendorService.getVendorDetailsByName(vendorName);
    }

    @PutMapping("/{vendorId}")
    public ResponseEntity<?> updateVendor(@RequestBody VendorDetails vendorDetails ,@PathVariable(value = "vendorId") int vendorId){
        return ResponseEntity.ok(vendorService.updateVendor(vendorDetails,vendorId));
    }




}
