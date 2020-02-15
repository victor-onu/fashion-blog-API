package com.victor.fashionblog.repository;

import com.victor.fashionblog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long PostId, Pageable pageable);
    Optional<Comment> findByIdAndPostId(Long id, Long PostId);
}
