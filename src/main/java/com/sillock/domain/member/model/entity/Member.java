package com.sillock.domain.member.model.entity;

import com.sillock.core.auth.jwt.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    private ObjectId memberId;

    private String nickName;

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
