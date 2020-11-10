package com.cognizant.truckservice.controller;

import com.cognizant.truckservice.model.Truck;
import com.cognizant.truckservice.service.TruckService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/v1/truck")
public class TruckController {

    @Autowired
    TruckService truckService;

    @GetMapping
    @ApiOperation("Returns all the Truck Details")
    public ResponseEntity<List<Truck>> getAllTruckDetails(){
        return ResponseEntity.ok(truckService.getAllTruckDetails());
    }

    @PostMapping
    @ApiOperation("Register a new Truck")
    public ResponseEntity<?> registerTruck(@RequestBody Truck truck){
        return ResponseEntity.status(HttpStatus.CREATED).body(truckService.registerTruck(truck));
    }
    @DeleteMapping
    @ApiIgnore
    public void deleteAll(){
        truckService.deleteAll();
    }

    @DeleteMapping("/{truckId}")
    @ApiOperation("Delete the Truck Based on Truck Number")
    public  ResponseEntity<?> deleteTruck(@PathVariable(value = "truckId") int truckId){
        truckService.deleteTruck(truckId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{truckNumber}")
    public ResponseEntity<Truck> getByTruckNumber(@PathVariable(value = "truckNumber") String truckNumber){
        return ResponseEntity.ok(truckService.getByTruckNumber(truckNumber));
    }

    @PutMapping("/{truckId}")
    public ResponseEntity<?> updateTruckDetails(@RequestBody Truck truck, @PathVariable(value = "truckId") int truckId){
        return  ResponseEntity.ok(truckService.updateTruckDetails(truck, truckId));
    }
}
