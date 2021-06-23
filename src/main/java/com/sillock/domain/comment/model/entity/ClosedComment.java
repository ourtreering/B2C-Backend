package com.sillock.domain.comment.model.entity;

import com.sillock.domain.event.model.entity.ClosedEvent;
import com.sillock.domain.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClosedComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "closed_comment_id")
    private Long closedCommentId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(nullable = false, name = "closed_event_id")
    private ClosedEvent closedEvent;

    @Column(nullable = false, name = "comment")
    private String comment;
    @Column(nullable = false, name = "image")
    private String image;
    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate;
    @Column(nullable = false, name = "mod_date")
    private LocalDateTime modDate;
    @Column(nullable = false, name = "event_date")
    private LocalDateTime eventDate;
}