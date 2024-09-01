package com.corecoda.ikollect.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationExceptionHandler extends RuntimeException {

    private final Set<String> errorMessages;
}
