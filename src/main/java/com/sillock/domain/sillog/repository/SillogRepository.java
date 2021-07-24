package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Sillog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SillogRepository extends MongoRepository<Sillog, ObjectId> {
//    List<Sillog> findAllByAuthorId(ObjectId memberId);
//    List<Sillog> findByAuthorIdAndTitle(ObjectId memberId, String title);
}
