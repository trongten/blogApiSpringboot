package com.trong10.blog.controllers;

import com.trong10.blog.enums.RoleName;
import com.trong10.blog.exceptions.ApiException;
import com.trong10.blog.jwt.JwtTokenProvider;
import com.trong10.blog.models.Role;
import com.trong10.blog.models.User;
import com.trong10.blog.payload.LoginRequest;
import com.trong10.blog.payload.LoginResponse;
import com.trong10.blog.payload.SignUpRequest;
import com.trong10.blog.repositories.RoleRepository;
import com.trong10.blog.repositories.UserRepository;
import com.trong10.blog.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        String name = signUpRequest.getName().toLowerCase();
        String username = signUpRequest.getUsername().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        User user = new User(name, username, email, password);

        //Default role is ROLE_USER
        List<Role> roleList = new ArrayList<>();
        Role role =  roleRepository.findByName(RoleName.ROLE_USER);
        roleList.add(role);
        user.setRoles(roleList);

        User result = userRepository.save(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
