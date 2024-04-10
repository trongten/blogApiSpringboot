package com.trong10.blog.controllers;

import com.trong10.blog.models.User;
import com.trong10.blog.payload.UpdateProfileRequest;
import com.trong10.blog.security.CurrentUser;
import com.trong10.blog.security.CustomUserDetails;
import com.trong10.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUSerProfile(@PathVariable(value = "id") long id) {
        User userProfile = userService.get(id);
        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<User> updateUSerProfile(@PathVariable(value = "id") long id, @Valid @RequestBody UpdateProfileRequest request, @CurrentUser CustomUserDetails customUserDetails) {
        User userProfile = userService.update(id, request, customUserDetails);
        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }
}
