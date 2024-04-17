package org.example.wepaybackend.appuser.services;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.appuser.models.AppUser;
import org.example.wepaybackend.appuser.models.BusinessUser;
import org.example.wepaybackend.appuser.models.ParticularUser;
import org.example.wepaybackend.appuser.models.data.AccountType;
import org.example.wepaybackend.appuser.models.data.AppUserRole;
import org.example.wepaybackend.appuser.repositorys.BusinessRepository;
import org.example.wepaybackend.appuser.repositorys.ParticularRepository;
import org.example.wepaybackend.appuser.repositorys.UserRepository;
import org.example.wepaybackend.appuser.reqests.AuthenticationRequest;
import org.example.wepaybackend.appuser.reqests.AuthenticationResponse;
import org.example.wepaybackend.appuser.reqests.BusinessRegisterRequest;
import org.example.wepaybackend.appuser.reqests.ParticularRegisterRequest;
import org.example.wepaybackend.security.config.JwtService;
import org.example.wepaybackend.token.Token;
import org.example.wepaybackend.token.TokenRepository;
import org.example.wepaybackend.token.TokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final BusinessRepository businessRepository;
    private final ParticularRepository particularRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse registerBusiness(BusinessRegisterRequest request) {
        var user = BusinessUser.builder()
                .storeName(request.getStoreName())
                .industry(request.getIndustry())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .appUserRole(AppUserRole.USER)
                .accountType(AccountType.BUSINESSS)
                .build();

        var savedUser = businessRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .id(userRepository.findId(savedUser.getEmail()))
                .accessToken(jwtToken)
                .build();
}

public AuthenticationResponse registerParticular(ParticularRegisterRequest request) {
    var user = ParticularUser.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .phone(request.getPhone())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .appUserRole(AppUserRole.USER)
            .accountType(AccountType.PARTICULAR)
            .build();

    var savedUser = particularRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
            .id(userRepository.findId(savedUser.getEmail()))
            .accessToken(jwtToken)
            .build();
}
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .id(userRepository.findId(user.getEmail()))
                .accessToken(jwtToken)
                .build();
    }

    public void saveUserToken(AppUser appUser, String jwtToken) {
        var token = Token.builder()
                .user(appUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(AppUser user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        logger.info(validUserTokens.toString());
        logger.info(user.toString());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
