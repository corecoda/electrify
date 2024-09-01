package com.corecoda.ikollect.services;


import com.corecoda.ikollect.entities.Company;
import com.corecoda.ikollect.entities.SystemAdmin;
import com.corecoda.ikollect.entities.TokenType;
import com.corecoda.ikollect.entities.common.enums.AccountStatus;
import com.corecoda.ikollect.entities.common.enums.AccountType;
import com.corecoda.ikollect.models.*;
import com.corecoda.ikollect.repositories.CompanyRespository;
import com.corecoda.ikollect.repositories.SystemAdminRepository;
import com.corecoda.ikollect.settings.ApplicationSettings;
import com.corecoda.ikollect.utils.JwtService;
import com.corecoda.ikollect.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final JwtService jwtService;
    private final SystemAdminRepository systemAdminRepository;
    private final CompanyRespository companyRespository;
    private final ApplicationSettings applicationSettings;
    private final PasswordEncoder passwordEncoder;
    private final ObjectsValidator<CreateAdminUserRequest> createAdminUserRequestObjectsValidator;
    private final ObjectsValidator<AdminLoginRequest> adminLoginRequestObjectsValidator;
    public ResponseModel CreateAdminUser(
            CreateAdminUserRequest request
    ) {
        createAdminUserRequestObjectsValidator.validate(request);

        var user = systemAdminRepository.findByUsernameIgnoreCase(request.getUsername());
        if (user != null) {
            return ResponseModel.failure("Admin user already exist");
        }
        var newUser =  SystemAdmin.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .authorizationPin(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .retryCount(0)
                .accountStatus(AccountStatus.ACTIVE)
                .accountType(AccountType.SUPERADMIN)
                .createdAt(LocalDateTime.now())
                .createdBy("SYSTEM")// Set the current date and time
                .build();
        systemAdminRepository.save(newUser);
        log.info("Created admin user: {}", newUser);
        return ResponseModel.success("Admin user created");
    }

    public ResponseModel authenticate(AdminLoginRequest request) {

        adminLoginRequestObjectsValidator.validate(request);

        var user = systemAdminRepository.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseModel.failure("Invalid credentials");
        }
        else{
            if (!passwordEncoder.matches(request.getPassword(), user.getAuthorizationPin())) {
                Integer maxPasswordTries = applicationSettings.getMaximumPasswordTries();
                user.setRetryCount(user.getRetryCount() + 1);

                if (user.getRetryCount() >= maxPasswordTries) {
                    var msg = String.format("Your account is now disabled due to %d incorrect login attempts. Contact your company for assistance.", maxPasswordTries);
                    user.setAccountStatus(AccountStatus.INACTIVE);
                    user.setDisableReason(msg);
                    user.setLastModified(LocalDateTime.now());

                    systemAdminRepository.save(user);
                    return ResponseModel.failure(msg, null, FaultMode.UNAUTHORIZED);
                }
                else {
                    var attemptsLeft = maxPasswordTries - user.getRetryCount();
                    var message = String.format("Invalid login credentials. You have  %s attempts left.", attemptsLeft);
                    systemAdminRepository.save(user);
                    return ResponseModel.failure(message, null, FaultMode.UNAUTHORIZED);
                }
            }
            else{
                // Add extra claims
                Map<String, Object> extraClaims = new HashMap<>();
                extraClaims.put("role", user.getAccountType());
                extraClaims.put("username", user.getUsername());
                extraClaims.put("companyId", user.getCompany());
                extraClaims.put("firstName", user.getFirstName());
                extraClaims.put("lastName", user.getLastName());
                extraClaims.put("userId", user.getId());
                var jwtToken = jwtService.generateToken(extraClaims, user);

                jwtService.revokeAllUserTokens(user.getId());
                jwtService.saveUserToken(user.getId(), jwtToken);

                var refreshToken = jwtService.generateRefereshToken(extraClaims, user);

                Optional<Company> company = Optional.ofNullable(user.getCompany())
                        .map(Company::getId)
                        .flatMap(companyRespository::findById);
                String companyName = Optional.ofNullable(user.getCompany())
                        .map(Company::getName)
                        .orElse(null);

                var authresponse =  AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .accountType(user.getAccountType())
                        .companyname(companyName)
                        .firstname(user.getFirstName())
                        .lastname(user.getLastName())
                        .tokenType(TokenType.BEARER)
                        .build();

                return ResponseModel.success("Login Successful", authresponse, FaultMode.NONE);

            }
        }
    }
}
