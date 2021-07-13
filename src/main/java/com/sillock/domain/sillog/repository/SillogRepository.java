package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Sillog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SillogRepository extends MongoRepository<Sillog, String> {
    List<Sillog> findAllById(String memberId);
    List<Sillog> findSillogsByAuthor(String author);
    List<Sillog> findByIdAndTitle(String memberId, String title);
}
