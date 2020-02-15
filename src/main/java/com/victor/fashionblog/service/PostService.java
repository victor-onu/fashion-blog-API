package com.victor.fashionblog.service;

import com.victor.fashionblog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface PostService {


    Page<Post> findAll(Pageable pageable);

    Post save(Post post);

    Post updatePost(Long postId, Post postRequest);

    ResponseEntity<Object> delete(Long postId);


}
