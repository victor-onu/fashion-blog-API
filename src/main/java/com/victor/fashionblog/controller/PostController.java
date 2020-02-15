package com.victor.fashionblog.controller;

import com.victor.fashionblog.apierror.ApiError;
import com.victor.fashionblog.model.Post;
import com.victor.fashionblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    String noErrorMessage = "No Error";
    String noDebugMessage = "No Error to debug";

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @PostMapping("/posts")
    public ResponseEntity<ApiError<Post>> createPost(@Valid @RequestBody Post post) {
        Post newPost = postService.save(post);
        ApiError apiError = new ApiError(HttpStatus.CREATED);
        apiError.setMessage("Post Successfully created");
        return new ResponseEntity<>(apiError, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<ApiError <Post>> updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
       Post postToUpdate = postService.updatePost(postId, postRequest);
        ApiError apiError = new ApiError(HttpStatus.OK);
        apiError.setMessage("Post Successfully updated");
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        Long deletedId = postService.delete(postId);
        ApiError apiError = new ApiError(HttpStatus.OK);
        apiError.setMessage("Post Successfully deleted");
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }

}
