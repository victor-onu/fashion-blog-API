package com.victor.fashionblog.service;

import com.victor.fashionblog.exception.ResourceNotFoundException;
import com.victor.fashionblog.model.Post;
import com.victor.fashionblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class PostServiceImpl implements PostService {
   @Autowired
    PostRepository postRepository;

   @Autowired
   Post post;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, Post postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setPrice(postRequest.getPrice());
            post.setUpdatedAt(post.getUpdatedAt());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public ResponseEntity<Object> delete(Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
