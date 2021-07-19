package com.sillock.domain.member.model.entity;

import com.sillock.core.auth.jwt.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="nick_name", nullable = false)
    private String nickName;

    @Column(name="email", length = 30, nullable = false)
    private String email;

    @Column(name="password", length = 20, nullable = false)
    private String password;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="profile_image", nullable = false)
    private String profileImage;

    @Column(name="gender", nullable = false)
    private String gender;

    @Builder.Default
    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate = LocalDateTime.now();
}
