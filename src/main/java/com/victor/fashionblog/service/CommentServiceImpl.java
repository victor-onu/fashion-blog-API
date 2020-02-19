package com.victor.fashionblog.service;

import com.victor.fashionblog.exception.CustomException;
import com.victor.fashionblog.exception.ResourceNotFoundException;
import com.victor.fashionblog.model.Comment;
import com.victor.fashionblog.repository.CommentRepository;
import com.victor.fashionblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Page<Comment> findCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public Comment createAComment(Long postId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new CustomException("PostId " + postId + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment commentRequest) {

        if(!postRepository.existsById(postId)) {
            throw new CustomException("PostId " + postId + " not found", HttpStatus.NOT_FOUND);
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new CustomException("CommentId " + commentId + "not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Object> deleteAComment(Long postId, Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new CustomException("Comment not found with id " + commentId + " and postId " + postId, HttpStatus.NOT_FOUND));
    }

}
