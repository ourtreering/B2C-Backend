package com.sillock.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class IssuedToken {
    @Id
    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="event_id", nullable = false)
    private Long eventId;

}
