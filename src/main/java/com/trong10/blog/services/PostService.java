package com.trong10.blog.services;

import com.trong10.blog.models.Post;
import com.trong10.blog.models.User;
import com.trong10.blog.payload.PostRequest;
import com.trong10.blog.security.CustomUserDetails;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface PostService {
    public List<Post> getAll();
    public Post get(long id);
    public Post create(PostRequest postRequest, CustomUserDetails customUserDetails);
    public Post update(long id,PostRequest postRequest, CustomUserDetails customUserDetails);
    public Post delete(long id);
}
