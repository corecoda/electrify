package com.corecoda.ikollect.settings;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtSettings  {
    @Value("${spring.application.settings.jwtsettings.secret-key}")
    private String secretKey;
    @Value("${spring.application.settings.jwtsettings.expiration}")
    private long jwtExpiration;
    @Value("${spring.application.settings.jwtsettings.refresh-token.expiration}")
    private long refreshExpiration;
}
