package com.sillock.comment.domain.infra.repository;

import com.sillock.comment.domain.OpenComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenCommentRepository extends JpaRepository<OpenComment,Long> {
}
