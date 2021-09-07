package com.sillock.domain.member.repository;

import com.sillock.domain.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Service
public class MemberMongoTemplate {
    private final MongoTemplate mongoTemplate;

    @Transactional(readOnly = true)
    public Member findByEmail(String email){
        Member member = mongoTemplate.findOne(query(where("email").is(email)), Member.class);
        return member;
    }
}
