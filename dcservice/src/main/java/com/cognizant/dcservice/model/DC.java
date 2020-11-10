package com.cognizant.dcservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dc_details")
public class DC implements Serializable {
    @Id
    @Column(name = "dc_number")
    int dcNumber;
    @Column(name = "dc_city", nullable = false)
    String dcCity;
    @Column(name="dc_type", nullable = false)
    String dcType;

    @JsonIgnore
    @OneToMany(mappedBy = "dc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DcSlots> dcSlotsSet;


}
