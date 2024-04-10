package com.trong10.blog.services.impl;

import com.trong10.blog.exceptions.ResourceNotFoundException;
import com.trong10.blog.models.User;
import com.trong10.blog.payload.UpdateProfileRequest;
import com.trong10.blog.repositories.UserRepository;
import com.trong10.blog.security.CustomUserDetails;
import com.trong10.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(long id, UpdateProfileRequest signUpRequest, CustomUserDetails customUserDetails) {
        if(customUserDetails.getUser().getId() != id){
            throw new ResourceNotFoundException("User","Forbidden",id);
        }
        User user = this.get(id);
        if(signUpRequest.getName() != null) {
            user.setName(signUpRequest.getName());
        }
        if(signUpRequest.getPassword() != null){
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public boolean delete(long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
