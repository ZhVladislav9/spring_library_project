package com.project.library.controllers.auth;

import com.project.library.service.WebuserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
@Log4j2
public class WebuserRestController {
    @Autowired
    private WebuserServiceImpl webuserServiceImpl;
    @PostMapping("/register/user")
    @Operation(summary = "Зарегистрировать нового пользователя", description = "Зарегистрировать нового пользователя")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterRequest request) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(webuserServiceImpl.addUser(request));
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Зарегистрировать нового админа", description = "Зарегистрировать нового админа")
    public ResponseEntity<AuthenticationResponse> addAdmin(@RequestBody RegisterRequest request) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(webuserServiceImpl.addAdmin(request));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Ауенитификация", description = "Ауенитификация")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(webuserServiceImpl.authenticate(request));
    }

}