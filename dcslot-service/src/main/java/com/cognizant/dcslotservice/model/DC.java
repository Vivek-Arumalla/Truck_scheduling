package com.cognizant.dcslotservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dc_details")
public class DC implements Serializable {
    @Id
    @Column(name = "dc_number")
    @ApiModelProperty(hidden = true)
    int dcNumber;
    @Column(name = "dc_city", nullable = false)
    String dcCity;
    @Column(name="dc_type", nullable = false)
    @ApiModelProperty(hidden = true)
    String dcType;

    @JsonIgnore
    @OneToMany(mappedBy = "dc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DcSlots> dcSlotsSet;


}
