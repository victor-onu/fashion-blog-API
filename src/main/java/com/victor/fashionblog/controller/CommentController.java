package com.victor.fashionblog.controller;

import com.victor.fashionblog.apierror.ApiError;
import com.victor.fashionblog.model.Comment;
import com.victor.fashionblog.model.Post;
import com.victor.fashionblog.service.CommentService;
import com.victor.fashionblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    String noErrorMessage = "No Error";
    String noDebugMessage = "No Error to debug";

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiError< Page<Comment>>> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Pageable pageable) {
        Page<Comment> comments = commentService.findCommentsByPostId(postId, pageable);
        ApiError apiError = new ApiError(HttpStatus.OK);
        apiError.setMessage("Comments retrieved successfully");
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiError <Comment>> createComment(@PathVariable (value = "postId") Long postId, @Valid @RequestBody Comment comment) {
      Comment newComment = commentService.createAComment(postId, comment);
        ApiError apiError = new ApiError(HttpStatus.OK);
        apiError.setMessage("Comments retrieved successfully");
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiError <Comment>> updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
       Comment commentToUpdate = commentService.updateComment(postId, commentId, commentRequest);
        ApiError apiError = new ApiError(HttpStatus.OK);
        apiError.setMessage("Comment updated successfully");
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        commentService.deleteAComment(postId, commentId);
        ApiError apiError = new ApiError(HttpStatus.OK);
        apiError.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }

}
