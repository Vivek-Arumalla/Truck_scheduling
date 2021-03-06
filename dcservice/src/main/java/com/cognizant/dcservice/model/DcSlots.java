package com.cognizant.dcservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dc_slots")
public class DcSlots implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dc_slot_number")
    int dcSlotNumber;
    @Column(name = "time_slot",nullable = false)
    String timeSlot;
    @Column(name = "max_trucks", nullable = false)
    int maxTrucks;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "dc_number",nullable = false)
    private DC dc;


}
