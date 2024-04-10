package com.trong10.blog.services;

import com.trong10.blog.models.Post;
import com.trong10.blog.models.User;
import com.trong10.blog.payload.PostRequest;
import com.trong10.blog.payload.SignUpRequest;
import com.trong10.blog.payload.UpdateProfileRequest;
import com.trong10.blog.security.CustomUserDetails;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    public List<User> getAll();
    public User get(long id);
    public User create(User user);
    public User update(long id, UpdateProfileRequest signUpRequest, CustomUserDetails customUserDetails);
    public boolean delete(long id);
}
