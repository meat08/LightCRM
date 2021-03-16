package ru.lightcrm.controllers;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
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
import ru.lightcrm.services.interfaces.UserService;
import ru.lightcrm.utils.JwtRequest;
import ru.lightcrm.utils.JwtResponse;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserService userService;


  public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
    String username = jwtRequest.getUsername();
    System.out.println("Имя " + username + " сырой пароль " + jwtRequest.getPassword());

    try {
      User user = userService.getByUsername(username).get();

      if (!user.getEnabled()) {
        return new ResponseEntity<>(
            new LightCrmError(HttpStatus.UNAUTHORIZED.value(), "Account deleted"),
            HttpStatus.UNAUTHORIZED);
      }
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
              jwtRequest.getPassword()));
    } catch (BadCredentialsException | NoSuchElementException ex) {
      return new ResponseEntity<>(
          new LightCrmError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"),
          HttpStatus.UNAUTHORIZED);
    }
    UserDetails userDetails = userService.loadUserByUsername(username);
    String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
  }
}
