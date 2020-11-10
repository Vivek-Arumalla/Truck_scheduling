package com.cognizant.dcservice.controller;


import com.cognizant.dcservice.exception.DcNotFoundException;
import com.cognizant.dcservice.model.DC;
import com.cognizant.dcservice.service.DCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/dc")
public class DCController {
    @Autowired
    private DCService dcService;

    @GetMapping
    public ResponseEntity<List<DC>> getDCDetails(){
        return ResponseEntity.ok(dcService.getDcDetails());
    }

    @PostMapping
    public ResponseEntity<?> insertDcDetails(@RequestBody DC dc) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dcService.insertDcDetails(dc));
    }
    @DeleteMapping
    public void deleteAll(){
        dcService.deleteAll();
    }

    @GetMapping("/id/{dcNumber}")
    public ResponseEntity<DC> getDcByNumber(@PathVariable(value = "dcNumber") int dcNumber) {
        return ResponseEntity.ok(dcService.getDcByNumber(dcNumber));
    }
    @GetMapping("/name/{dcCityName}")
    public ResponseEntity<DC> getDcByCityName(@PathVariable(value = "dcCityName") String dcCityName) {
        return ResponseEntity.ok(dcService.getDcByCityName(dcCityName));
    }

    @DeleteMapping("/{dcNumber}")
    public ResponseEntity<?> deleteDc(@PathVariable(value="dcNumber") int dcNumber) {
        dcService.deleteDc(dcNumber);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{dcNumber}")
    public ResponseEntity<?> updateDcDetails(@RequestBody DC dc,@PathVariable(value="dcNumber") int dcNumber){
        return ResponseEntity.ok(dcService.updateDcDetails(dc,dcNumber));
    }

}
