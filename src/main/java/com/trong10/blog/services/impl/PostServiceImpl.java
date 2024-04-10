package com.trong10.blog.services.impl;

import com.trong10.blog.exceptions.ResourceNotFoundException;
import com.trong10.blog.models.Post;
import com.trong10.blog.models.User;
import com.trong10.blog.payload.PostRequest;
import com.trong10.blog.repositories.PostRepository;
import com.trong10.blog.repositories.UserRepository;
import com.trong10.blog.security.CustomUserDetails;
import com.trong10.blog.services.PostService;
import com.trong10.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post get(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public Post create(PostRequest postRequest, CustomUserDetails customUserDetails) {
        User user = userService.get(customUserDetails.getUser().getId());
        Post post = new Post();
        post.setUser(user);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return postRepository.save(post);
    }

    @Override
    public Post update(long id,PostRequest postRequest, CustomUserDetails customUserDetails) {
        Post post = this.get(id);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return postRepository.save(post);
    }

    @Override
    public Post delete(long id) {
        Post post = this.get(id);
        postRepository.deleteById(id);
        return post;
    }
}
