package com.corecoda.ikollect.entities;


import com.corecoda.ikollect.entities.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company extends BaseEntity {
    @Column(
            name = "Code",
            length = 3,
            unique = true
    )
    private String code;

    @Column(
            name = "Name",
            length = 50,
            unique = true
    )
    private String name;
    private Integer aprovalStatus;
    private Boolean hasCommissionCharges;
    private String implementation;
    @Column(
            name = "AccountNumber",
            length = 10,
            unique = true
    )
    private  String accountNumber;
    private BigDecimal commissionCharges;
    @OneToMany(mappedBy = "company")
    private List<SystemAdmin> systemAdmins;
    @OneToMany(mappedBy = "company")
    private List<ApplicationUser> user;
}
