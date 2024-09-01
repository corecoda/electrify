package com.corecoda.ikollect.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationSettings {
    @Value("${spring.application.settings.applicationsettings.maximumPasswordTries}")
    private Integer maximumPasswordTries;
    @Value("${spring.application.settings.applicationsettings.iv}")
    private String IV;
    @Value("${spring.application.settings.applicationsettings.key}")
    private String secretKey;
}
