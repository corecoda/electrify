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
public class AuditLog extends BaseEntity {

    private String action;
    private String controllerName;
    private String entry;
    private String ipAddress;
    private String entity;
    private String oldValue;
    private String newValue;
    private String affectedColumn;
    private String email;
}
