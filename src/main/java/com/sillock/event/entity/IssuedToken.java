package com.sillock.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class IssuedToken {


    @JoinColumn(name = "member_id")
    @Column(name="member_id", nullable = false)
    private Long memberId;

    @JoinColumn(name = "event_id")
    @Column(name="event_id", nullable = false)
    private Long eventId;

}
