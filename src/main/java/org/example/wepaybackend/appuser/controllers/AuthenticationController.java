package org.example.wepaybackend.appuser.controllers;

import lombok.AllArgsConstructor;
import org.example.wepaybackend.appuser.reqests.AuthenticationRequest;
import org.example.wepaybackend.appuser.reqests.AuthenticationResponse;
import org.example.wepaybackend.appuser.reqests.BusinessRegisterRequest;
import org.example.wepaybackend.appuser.reqests.ParticularRegisterRequest;
import org.example.wepaybackend.appuser.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/business")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody BusinessRegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerBusiness(request));
    }

    @PostMapping("/register/particular")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody ParticularRegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerParticular(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
