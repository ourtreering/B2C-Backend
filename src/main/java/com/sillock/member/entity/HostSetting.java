package com.sillock.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class HostSetting {
    @Id
    @OneToOne
    @JoinColumn(name = "member_id")
    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="remain_count", nullable = false)
    private Long remainCount;
}
