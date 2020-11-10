package com.cognizant.vendorservice.repository;

import com.cognizant.vendorservice.model.VendorDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends CrudRepository<VendorDetails,Integer> {
    @Query(value = "select * from vendor_details where vendor_email = ?1",nativeQuery = true)
    List<VendorDetails> findByEmail(String vendorEmail);

    VendorDetails findByVendorName(String vendorName);
}
