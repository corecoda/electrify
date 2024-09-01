package com.corecoda.ikollect.entities;


import com.corecoda.ikollect.entities.common.BaseEntity;
import com.corecoda.ikollect.entities.common.enums.AccountStatus;
import com.corecoda.ikollect.entities.common.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String photoUrl;
    private Boolean phoneVerified;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private String companyProductNumber;
    @Column(
            name = "VirtualAccountNumber",
            length = 10,
            unique = true
    )
    private String virtualAccountNumber;
    @Column(
            name = "NubanAccount",
            length = 10,
            unique = true
    )
    private String nubanAccount;
    @Column(
            name = "NIN",
            length = 11,
            unique = true
    )
    private String NIN;
    @Column(
            name = "BVN",
            length = 11,
            unique = true
    )
    private String BVN;
    private String disableReason;
    private String isFirstLogin;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;
    @Column(nullable = true)
    private UUID companyId;
}
