package com.cognizant.truckservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "truck_details")
public class Truck  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "truck_id")
    @ApiModelProperty(hidden = true)
    int truckId;
    @Column(name = "truck_number",nullable = false,unique = true)
    String truckNumber;
    @Column(name = "truck_name")
    String truckName;
    @Column(name = "truck_type")
    String truckType;


}
