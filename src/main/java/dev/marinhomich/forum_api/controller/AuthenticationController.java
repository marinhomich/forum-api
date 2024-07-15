package dev.marinhomich.forum_api.controller;

import dev.marinhomich.forum_api.dto.JwtTokenDto;
import dev.marinhomich.forum_api.dto.AuthenticationDto;
import dev.marinhomich.forum_api.entity.User;
import dev.marinhomich.forum_api.security.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<JwtTokenDto> authenticateUser(@RequestBody @Valid AuthenticationDto dto) {
        var tokenJwt = authenticateAndGetJwtToken(dto);

        return ResponseEntity.ok(tokenJwt);
    }

    private JwtTokenDto authenticateAndGetJwtToken(AuthenticationDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);
        var tokenJwt = jwtTokenService.generateToken((User) authentication.getPrincipal());

        return new JwtTokenDto(tokenJwt);
    }
}
