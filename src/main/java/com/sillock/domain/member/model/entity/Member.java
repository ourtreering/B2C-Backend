package com.sillock.domain.member.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "member")
public class Member {
    @Id
    private ObjectId id;

    private String identifier;

    private String nickname;

    private String email;

    private String password;

    private LocalDate birth;

    private String profileImage;

    private String gender;

    @Indexed
    @Builder.Default
    private LocalDateTime regDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime modDate = LocalDateTime.now();
}
