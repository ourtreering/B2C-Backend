package com.sillock.comment.domain;

import com.sillock.event.domain.OpenEvent;
import com.sillock.member.domain.entity.Member;
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
public class OpenComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "open_comment_id")
    private Long openCommentId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(nullable = false, name = "open_event_id")
    private OpenEvent openEvent;

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