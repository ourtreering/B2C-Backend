package com.sillock.event.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Getter
@NoArgsConstructor
@Embeddable
public class IssuedTokenPK  implements Serializable {

    private Long memberId;
    private Long eventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuedTokenPK that = (IssuedTokenPK) o;
        return memberId.equals(that.memberId) && eventId.equals(that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, eventId);
    }

    @Builder
    public IssuedTokenPK(Long memberId, Long eventId){
        this.memberId = memberId;
        this.eventId = eventId;
    }
    //    @JoinColumn(name = "member_id")
//    @Column(name="member_id", nullable = false)
//    private Long memberId;
//
//    @JoinColumn(name = "event_id")
//    @Column(name="event_id", nullable = false)
//    private Long eventId;
}