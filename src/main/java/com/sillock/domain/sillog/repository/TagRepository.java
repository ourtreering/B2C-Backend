package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.Tag;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, ObjectId> {
}
