package com.spring5.nestedjson.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class ExciseServiceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uesm_id")
    private Long uesmId;

    @Column(name = "uesm_parent_id")
    private Long uesmParentId;

    @Column(name = "uesm_service_name")
    private String uesmServiceName;

    @Column(name = "uesm_amount_excluding")
    private String uesmAmountExcluding;

    @Column(name = "uesm_rate")
    private String uesmRate;

    @Column(name = "uesm_excise")
    private String uesmExcise;

}
