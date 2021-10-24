package com.sillock.domain.tag.repository;

import com.sillock.domain.tag.model.entity.TagInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagInfoRepository extends MongoRepository<TagInfo, ObjectId> {
}
