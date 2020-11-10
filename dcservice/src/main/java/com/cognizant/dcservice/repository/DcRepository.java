package com.cognizant.dcservice.repository;
import com.cognizant.dcservice.model.DC;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DcRepository extends CrudRepository<DC,Integer> {

    public Optional<DC> findByDcCity(String dcCity);
}
