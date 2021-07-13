package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Qna;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QnaRepository extends MongoRepository<Qna, String> {
}
