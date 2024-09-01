package com.corecoda.ikollect.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class iKollectNotFoundException extends RuntimeException {
    private final String message;
}
