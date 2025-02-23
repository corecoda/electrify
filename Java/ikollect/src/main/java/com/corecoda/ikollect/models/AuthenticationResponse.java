package com.corecoda.ikollect.models;

import com.corecoda.ikollect.entities.TokenType;
import com.corecoda.ikollect.entities.common.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private TokenType tokenType;
    private String firstname;
    private String lastname;
    private String email;
    private AccountType accountType;
    private String companyname;
}
