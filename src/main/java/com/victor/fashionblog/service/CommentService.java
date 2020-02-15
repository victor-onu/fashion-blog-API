package com.victor.fashionblog.service;

import com.victor.fashionblog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CommentService {
    Page<Comment> findCommentsByPostId(Long postId, Pageable pageable);

    Comment createAComment(Long postId, Comment comment);

    Comment updateComment(Long postId, Long commentId, Comment commentRequest);

    ResponseEntity<Object> deleteAComment(Long postId, Long commentId);
}
