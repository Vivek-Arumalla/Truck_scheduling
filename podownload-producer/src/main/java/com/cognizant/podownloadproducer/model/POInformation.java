package com.cognizant.podownloadproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class POInformation {
    int poNumber;
    String poDate;
    String poAddress;
    int poLineNumber;
    String itemName;
    int quantity;
}
