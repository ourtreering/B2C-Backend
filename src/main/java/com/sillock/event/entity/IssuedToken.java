package com.sillock.event.entity;

import com.sillock.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class IssuedToken{

    @EmbeddedId
    private IssuedTokenPK id;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("eventId")
    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @Builder
    public IssuedToken(IssuedTokenPK id){
        this.id = id;
    }

}


