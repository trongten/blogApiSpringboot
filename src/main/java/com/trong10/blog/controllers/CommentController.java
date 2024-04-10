package com.trong10.blog.controllers;

import com.trong10.blog.models.Comment;
import com.trong10.blog.payload.CommentRequest;
import com.trong10.blog.security.CurrentUser;
import com.trong10.blog.security.CustomUserDetails;
import com.trong10.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getListComment() {
        List<Comment> comments = commentService.getAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable(name = "id") Long id) {
        Comment comment = commentService.get(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Comment> createPost(@Valid @RequestBody CommentRequest request, @CurrentUser CustomUserDetails customUserDetails){
        Comment comment = commentService.create(request, customUserDetails);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Comment> updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody CommentRequest request, @CurrentUser CustomUserDetails customUserDetails){
        Comment post = commentService.update(id, request, customUserDetails);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Comment> deletePost(@PathVariable(name = "id") Long id, @CurrentUser CustomUserDetails customUserDetails) {
        Comment post = commentService.delete(id, customUserDetails);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
