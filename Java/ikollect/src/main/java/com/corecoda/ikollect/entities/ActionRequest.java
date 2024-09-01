package com.corecoda.ikollect.entities;

import com.corecoda.ikollect.entities.common.BaseEntity;
import com.corecoda.ikollect.entities.common.enums.AccountStatus;
import com.corecoda.ikollect.entities.common.enums.ActionStatus;
import com.corecoda.ikollect.entities.common.enums.EntityType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ActionRequest extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private AccountStatus actionType;
    private String entityId;
    @Enumerated(EnumType.STRING)
    private EntityType entity;
    private String maker;
    private LocalDateTime initiatedDate;
    private String initiationReason;
    private String makerActionPayload;
    private ActionStatus actionStatus;
    private String checker;
    private String checkerActionReason;
    private LocalDateTime checkedActionDate;
    private String entityName;
    private String action;
    private String additionalInfo;
    @Column(nullable = true)
    private UUID companyId;
}
