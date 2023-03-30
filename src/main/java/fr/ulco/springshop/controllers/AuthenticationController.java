package fr.ulco.springshop.controllers;

import fr.ulco.springshop.model.bo.UserBO;
import fr.ulco.springshop.model.dto.UserDTO;
import fr.ulco.springshop.model.form.LoginForm;
import fr.ulco.springshop.model.form.RegisterForm;
import fr.ulco.springshop.security.UserDetailsServiceInterface;
import fr.ulco.springshop.security.jwt.JwtResponse;
import fr.ulco.springshop.security.jwt.JwtTokenUtil;
import fr.ulco.springshop.service.core.UserServiceInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Tag(name = "Authentication", description = "The authentication API")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsServiceInterface userDetailsService;

    private UserServiceInterface userService;

    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = Routes.LOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = Routes.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) throws Exception {
        if (userService.findByEmail(registerForm.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        UserBO user = new UserBO();
        user.setEmail(registerForm.getEmail());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setFirstname(registerForm.getFirstName());
        user.setName(registerForm.getLastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setEnabled(true);

        user = userService.createUser(user);

        return ResponseEntity.ok(new UserDTO(user));
    }

    @RequestMapping(value = Routes.ME, method = RequestMethod.GET)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> me(Authentication authentication) {
        Optional<UserBO> user = userService.findByEmail(authentication.getName());
        return user.map(value -> ResponseEntity.ok(new UserDTO(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
