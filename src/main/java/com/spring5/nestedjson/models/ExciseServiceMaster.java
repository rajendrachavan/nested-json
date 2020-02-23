package com.spring5.nestedjson.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "excise_service_master")
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
