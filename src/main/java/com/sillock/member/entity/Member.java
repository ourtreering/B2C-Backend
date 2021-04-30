package com.sillock.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    @Column(name="email", length = 255)
    private String email;

    @Column(name = "is_active", nullable = true)
    private Boolean isActive;

    @Column(name="role", length = 30)
    private String role;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name="name", length = 10)
    private String name;
}
