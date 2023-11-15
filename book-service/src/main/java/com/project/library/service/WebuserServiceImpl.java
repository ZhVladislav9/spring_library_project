package com.project.library.service;


import com.project.library.controllers.auth.AuthenticationRequest;
import com.project.library.controllers.auth.AuthenticationResponse;
import com.project.library.controllers.auth.RegisterRequest;
import com.project.library.models.UserRole;
import com.project.library.models.Webuser;
import com.project.library.repository.WebuserRepository;
import com.project.library.service.interfaces.WebuserService;
import com.project.library.util.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class WebuserServiceImpl implements WebuserService {

    @Autowired
    private WebuserRepository webuserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse addUser(RegisterRequest request) throws NoSuchAlgorithmException {
        Webuser webuserToAdd = Webuser.builder()
                .id(UUID.randomUUID())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(UserRole.USER)
                .build();
        webuserRepository.save(webuserToAdd);
        var jwtToken = jwtService.generateToken(webuserToAdd);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse addAdmin(RegisterRequest request) throws NoSuchAlgorithmException {
        Webuser webuserToAdd = Webuser.builder()
                .id(UUID.randomUUID())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(UserRole.ADMIN)
                .build();
        webuserRepository.save(webuserToAdd);
        var jwtToken = jwtService.generateToken(webuserToAdd);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = webuserRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public Optional<Webuser> getUser(UUID id) {
        return webuserRepository.findById(id);
    }

}