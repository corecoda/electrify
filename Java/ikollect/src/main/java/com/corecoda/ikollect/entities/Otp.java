package com.corecoda.ikollect.entities;

import com.corecoda.ikollect.entities.common.BaseEntity;
import jakarta.persistence.Entity;
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
public class Otp extends BaseEntity {
    private UUID userId;
    private String code;
    private LocalDateTime expires;
    private Boolean status;
    private String email;
}
