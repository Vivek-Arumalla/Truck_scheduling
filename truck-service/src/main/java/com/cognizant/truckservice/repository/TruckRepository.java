package com.cognizant.truckservice.repository;

import com.cognizant.truckservice.model.Truck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Integer> {

    @Query(value = "select * from truck_details where truck_number=?1",nativeQuery = true)
    Truck findByTruckNumber(String truckNumber);
}
