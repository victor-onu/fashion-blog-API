package com.victor.fashionblog.service;

import com.victor.fashionblog.exception.CustomException;
import com.victor.fashionblog.exception.ResourceNotFoundException;
import com.victor.fashionblog.model.Post;
import com.victor.fashionblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
   @Autowired
    PostRepository postRepository;



    @Override
    public Page<Post> findAll(Pageable pageable) {

        Page allPosts = postRepository.findAll(pageable);
        if (allPosts.isEmpty()){
            throw new CustomException("No post available", HttpStatus.NOT_FOUND);
        }
        return allPosts;
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
//            post.setPrice(postRequest.getPrice());
            post.setUpdatedAt(post.getUpdatedAt());
            return postRepository.save(post);
        }).orElseThrow(() -> new CustomException("PostId " + postId + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Object> delete(Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new CustomException("PostId " + postId + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Post getOnePost(Long postId) throws  CustomException{
        Post foundPost = postRepository.findById(postId).orElse(null);

        if(foundPost ==null){
            throw new CustomException("PostId " + postId + " not found", HttpStatus.NOT_FOUND);
        }
        return foundPost;
    }


}
