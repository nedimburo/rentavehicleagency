package com.example.rentavehicleagency.auth.controllers;

import com.example.rentavehicleagency.auth.payloads.LoginRequestDto;
import com.example.rentavehicleagency.auth.payloads.LoginResponseDto;
import com.example.rentavehicleagency.auth.payloads.RegistrationRequestDto;
import com.example.rentavehicleagency.users.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/auth")
@Tags(value = @Tag(name = "Public | Auth"))
public class AuthController {

    private final UserService service;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto){
        return service.registerUser(registrationRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto){
        return service.loginUser(loginRequestDto);
    }
}
