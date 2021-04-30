package com.sillock.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Qualification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="qualification_id")
    private Long qualificationId;

    @Column(name="member_id")
    private Long memberId;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

}
