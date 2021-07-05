package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Sillog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SillogRepository extends MongoRepository<Sillog, String> {

}
