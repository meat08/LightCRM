package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.configs.JwtTokenUtil;
import ru.lightcrm.controllers.interfaces.AuthController;
import ru.lightcrm.entities.User;
import ru.lightcrm.exceptions.LightCrmError;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.services.interfaces.UserService;
import ru.lightcrm.utils.JwtRequest;
import ru.lightcrm.utils.JwtResponse;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        String username = jwtRequest.getUsername();
        try {
            User user = userService.getByUsername(username);
            if (!user.isEnabled()) {
                log.warn("User with login: {} deleted", username);
                return new ResponseEntity<>(
                        new LightCrmError(HttpStatus.UNAUTHORIZED.value(), "Account deleted"),
                        HttpStatus.UNAUTHORIZED);
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                            jwtRequest.getPassword()));
        } catch (BadCredentialsException | NoSuchElementException ex) {
            log.warn("User with login: {} has incorrect username or password  ", username);
            return new ResponseEntity<>(
                    new LightCrmError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"),
                    HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    new LightCrmError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
        UserDetails userDetails = userService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        log.info("Successfully created token for user login {}", username);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
