package com.sillock.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class MemberKlip {
    @Id
    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="eoa", length = 255, nullable = false)
    private String eoa;
}
