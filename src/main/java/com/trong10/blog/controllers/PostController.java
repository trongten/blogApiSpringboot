package com.trong10.blog.controllers;

import com.trong10.blog.models.Post;
import com.trong10.blog.payload.PostRequest;
import com.trong10.blog.security.CurrentUser;
import com.trong10.blog.security.CustomUserDetails;
import com.trong10.blog.services.PostService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getListPost() {
        List<Post> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(name = "id") Long id) {
        Post post = postService.get(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest request, @CurrentUser CustomUserDetails customUserDetails){
        Post post = postService.create(request, customUserDetails);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Post> updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody PostRequest request, @CurrentUser CustomUserDetails customUserDetails){
        Post post = postService.update(id, request, customUserDetails);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Post> deletePost(@PathVariable(name = "id") Long id) {
        Post post = postService.delete(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
