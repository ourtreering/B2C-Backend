package com.sillock.domain.member.repository;

import com.sillock.domain.member.model.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {
    Optional<Member> findById(ObjectId memberId);
    boolean existsByEmail(String email);
}
