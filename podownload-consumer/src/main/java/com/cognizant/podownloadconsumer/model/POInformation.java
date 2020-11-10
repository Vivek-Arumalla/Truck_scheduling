package com.cognizant.podownloadconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="po_details")
public class POInformation {
    @Id
    @Column(name = "po_number",unique = true,nullable = false)
    int poNumber;
    @Column(name= "po_date",nullable = false)
    String poDate;
    @Column(name="po_address",nullable = false)
    String poAddress;
    @Column(name = "po_line_number",nullable = false)
    int poLineNumber;
    @Column(name = "item_name",nullable = false)
    String itemName;
    @Column(name="quantity",nullable = false)
    int quantity;
}
