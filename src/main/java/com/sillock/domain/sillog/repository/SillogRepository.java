package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Sillog;
import com.sillock.domain.sillog.model.entity.SillogTitle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SillogRepository extends MongoRepository<Sillog, ObjectId> {
    List<Sillog> findAllByMemberId(ObjectId memberId);
    List<SillogTitle> findTitlesByMemberId(ObjectId memberId);
    List<Sillog> findAllByMemberIdAndTitle(ObjectId memberId, String title);
}
