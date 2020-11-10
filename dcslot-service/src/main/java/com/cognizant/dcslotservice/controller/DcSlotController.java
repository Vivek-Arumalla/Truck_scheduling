package com.cognizant.dcslotservice.controller;

import com.cognizant.dcslotservice.model.DcSlots;
import com.cognizant.dcslotservice.service.DcSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/dcslots")
public class DcSlotController {
    @Autowired
    DcSlotsService dcSlotsService;

    @GetMapping
    public ResponseEntity<List<DcSlots>> getDCSlotsDetails(){
        return ResponseEntity.ok(dcSlotsService.getDcSlotsDetails());
    }

    @GetMapping("/dcId/{dcNumber}")
    public ResponseEntity<List<DcSlots>> getDcSlotsDetailsByDcNumber(@PathVariable(value = "dcNumber") int dcNumber){
        return ResponseEntity.ok(dcSlotsService.getDcSlotsDetailsByDcNumber(dcNumber));
    }
    @GetMapping("/{slotNumber}")
    public ResponseEntity<DcSlots> getDcSlotsDetailsBySlotNumber(@PathVariable(value = "slotNumber") int slotNumber){
        return ResponseEntity.ok(dcSlotsService.getDcSlotsDetailsBySlotNumber(slotNumber));
    }

    @DeleteMapping
    public void deleteAll(){
        dcSlotsService.deleteAll();
    }

    @PostMapping
    public ResponseEntity<?> insertDcSlotsDetails(@RequestBody DcSlots dcSlots) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dcSlotsService.insertDcSlotDetails(dcSlots));
    }

    @PutMapping("/{slotNumber}")
    public ResponseEntity<?> updateDcSlotDetails(@RequestBody DcSlots dcSlots,@PathVariable(value="slotNumber") int slotNumber){
        return ResponseEntity.ok(dcSlotsService.updateDcSlotDetails(dcSlots,slotNumber));
    }

    @DeleteMapping("/{slotNumber}")
    public ResponseEntity<?> deleteDcSlot(@PathVariable(value = "slotNumber") int slotNumber) {
        dcSlotsService.deleteDcSlot(slotNumber);
        return ResponseEntity.ok().build();
    }



}

