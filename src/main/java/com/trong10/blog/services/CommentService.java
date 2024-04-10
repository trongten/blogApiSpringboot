package com.trong10.blog.services;

import com.trong10.blog.models.Comment;
import com.trong10.blog.payload.CommentRequest;
import com.trong10.blog.security.CustomUserDetails;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface CommentService {
    public List<Comment> getAll();
    public Comment get(long id);
    public Comment create(CommentRequest request, CustomUserDetails customUserDetails);
    public Comment update(long id,CommentRequest request, CustomUserDetails customUserDetails);
    public Comment delete(long id, CustomUserDetails customUserDetails);
}
