package com.sillock.domain.template.repository;

import com.sillock.domain.template.model.entity.Template;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TemplateRepository extends MongoRepository<Template, ObjectId> {
}
