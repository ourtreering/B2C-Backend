package com.sillock.domain.event.model.entity;

import com.sillock.domain.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
    @EmbeddedId
    private InvitationPK Id;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("closedEventId")
    @ManyToOne
    @JoinColumn(name = "closed_event_id")
    private ClosedEvent closedEvent;

    @Column(nullable = false, name = "is_approved")
    private Boolean isApproved;
    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate;
}