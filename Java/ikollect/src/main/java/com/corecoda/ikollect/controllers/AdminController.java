package com.corecoda.ikollect.controllers;


import com.corecoda.ikollect.models.AdminLoginRequest;
import com.corecoda.ikollect.models.CreateAdminUserRequest;
import com.corecoda.ikollect.models.ResponseModel;
import com.corecoda.ikollect.services.AdminService;
import com.corecoda.ikollect.utils.EncryptionUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminService adminService;
    private final EncryptionUtil encryptionUtil;
    @PostMapping("/create-admin-user")
    public ResponseEntity<ResponseModel> register(
            @RequestBody CreateAdminUserRequest request
    ){
        var response = adminService.CreateAdminUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> authenticate(
            @RequestBody AdminLoginRequest request
    ){
        var response = adminService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
