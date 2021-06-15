package com.sillock.comment.domain.infra.repository;

import com.sillock.comment.domain.ClosedComment;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ClosedCommentRepository extends JpaRepository<ClosedComment,Long> {
}
