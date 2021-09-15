package com.sillock.domain.sillog.repository;

import com.sillock.domain.sillog.model.entity.Sillog;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Repository
public class SillogMongo {
    private final MongoOperations mongoOps;

    public List<Sillog> findAllByMemberId(ObjectId memberId){
        return mongoOps.find(query(where("memberId").is(memberId)), Sillog.class);
    }

}
