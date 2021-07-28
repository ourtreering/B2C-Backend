package com.sillock.domain.tag.repository;

import com.sillock.domain.tag.model.entity.Tag;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends MongoRepository<Tag, ObjectId> {
    List<Tag> findTagsByMemberIdIsNull();
    List<Tag> findTagsByCategoryNot(String category);
}
