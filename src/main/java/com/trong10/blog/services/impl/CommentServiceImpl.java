package com.trong10.blog.services.impl;

import com.trong10.blog.exceptions.ResourceNotFoundException;
import com.trong10.blog.models.Comment;
import com.trong10.blog.models.Post;
import com.trong10.blog.models.User;
import com.trong10.blog.payload.CommentRequest;
import com.trong10.blog.repositories.CommentRepository;
import com.trong10.blog.repositories.PostRepository;
import com.trong10.blog.repositories.UserRepository;
import com.trong10.blog.security.CustomUserDetails;
import com.trong10.blog.services.CommentService;
import com.trong10.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment get(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment","Id", id));
    }

    @Override
    public Comment create(CommentRequest request, CustomUserDetails customUserDetails) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", request.getPostId()));
        User user = userService.get(customUserDetails.getUser().getId());

        Comment comment  = new Comment();
        comment.setContent(request.getContent());
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(long id, CommentRequest request, CustomUserDetails customUserDetails) {
        Comment comment = this.get(id);
        User user = userService.get(customUserDetails.getUser().getId());
        if(user.getId() != comment.getUser().getId()){
            throw new ResourceNotFoundException("Comment", "UserId", user.getId());
        }
        comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public Comment delete(long id, CustomUserDetails customUserDetails) {
        Comment comment = this.get(id);
        User user = userService.get(customUserDetails.getUser().getId());
        if(user.getId() != comment.getUser().getId()){
            throw new ResourceNotFoundException("Comment", "UserId", user.getId());
        }
        commentRepository.deleteById(id);
        return comment;
    }
}
