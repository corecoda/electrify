package com.corecoda.ikollect.entities;

import com.corecoda.ikollect.entities.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Settlement extends BaseEntity {

    private String companyBatchReference;
    private UUID companyId;
    private String beneficiaryAccountNumber;
    private BigDecimal amount;
    private String transactionReference;
    private String transactionType;
    private String status;
    private  String frequency;
    private String remarks;
}
