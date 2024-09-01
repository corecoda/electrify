package com.corecoda.ikollect.entities;


import com.corecoda.ikollect.entities.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionLog extends BaseEntity {
    private String transactionReference;
    private String code;
   private String description;
}
