package com.project.library.service.interfaces;

import com.project.library.controllers.auth.AuthenticationRequest;
import com.project.library.controllers.auth.AuthenticationResponse;
import com.project.library.controllers.auth.RegisterRequest;
import com.project.library.models.Webuser;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

public interface WebuserService {
    public AuthenticationResponse addUser(RegisterRequest request) throws NoSuchAlgorithmException;
    public AuthenticationResponse addAdmin(RegisterRequest request) throws NoSuchAlgorithmException;
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public Optional<Webuser> getUser(UUID id);
}
