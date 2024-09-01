package com.corecoda.ikollect.models;


import com.corecoda.ikollect.entities.common.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminUserRequest {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotNull(message = "Invalid Account Type")
    private AccountType accountType;
}
