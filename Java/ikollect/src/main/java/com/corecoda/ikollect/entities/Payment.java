package com.corecoda.ikollect.entities;


import com.corecoda.ikollect.entities.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends BaseEntity {

    private UUID userId;
    private UUID customerId;
    private String transactionReference;
    private LocalDateTime transactionDate;
    private String countryCode;
    private BigDecimal amount;
    private Integer transactionType;
    private LocalDateTime valueDate;
    private String nuban;
    private String sessionId;
    private String channelCode;
    private String beneficiaryAccountName;
    private String beneficiaryAccountNumber;
    private String originatorAccountName;
    private String originatorAccountNumber;
    private String narration;
    private String paymentReference;
    private String transactionStatus;
    private BigDecimal totalAmount;
    private BigDecimal chargeAmount;
}
