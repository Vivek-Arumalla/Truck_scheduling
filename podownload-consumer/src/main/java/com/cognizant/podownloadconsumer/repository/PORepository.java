package com.cognizant.podownloadconsumer.repository;

import com.cognizant.podownloadconsumer.model.POInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PORepository extends CrudRepository<POInformation,Integer> {

}
