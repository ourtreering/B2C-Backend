package com.sillock.member.entity;

import com.sillock.core.jwt.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="email", length = 255, nullable = false)
    private String email;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name="name", length = 15, nullable = false)
    private String name;

    @Column(name="unique_code", length = 7, nullable = false)
    private String uniqueCode;

    @Enumerated(EnumType.STRING)
    @Column(name="role", length = 20, nullable = false)
    private Role role;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate = LocalDateTime.now();

    @Builder
    public Member(Long memberId, String email, Boolean isActive, String name, String uniqueCode, Role role, LocalDateTime regDate, LocalDateTime modDate) {
        this.memberId = memberId;
        this.email = email;
        this.isActive = isActive;
        this.name = name;
        this.uniqueCode = uniqueCode;
        this.role= role;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
