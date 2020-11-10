package com.cognizant.vendorservice.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vendor_details")
public class VendorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    @ApiModelProperty(hidden = true)
    int vendorId;
    @Column(name = "vendor_name")
    String vendorName;
    @Column(name = "vendor_email", nullable = false,unique = true)
    String vendorEmail;
    @Column(name = "vendor_phone_number")
    String vendorPhoneNumber;
    @Column(name = "vendor_address")
    String vendorAddress;

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
}
