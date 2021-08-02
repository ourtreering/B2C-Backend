package com.sillock.domain.tag.repository;

import com.sillock.domain.tag.model.entity.MemberTagInfo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberTagInfoRepository extends MongoRepository<MemberTagInfo, ObjectId> {
    Optional<MemberTagInfo> findMemberTagInfoByMemberId(ObjectId memberId);
}
