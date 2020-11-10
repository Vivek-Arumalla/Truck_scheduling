package com.cognizant.dcslotservice.repository;



import com.cognizant.dcslotservice.model.DC;
import com.cognizant.dcslotservice.model.DcSlots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DcSlotsRepository extends CrudRepository<DcSlots,Integer> {
    public List<DcSlots> findByDc(DC dc);




}
