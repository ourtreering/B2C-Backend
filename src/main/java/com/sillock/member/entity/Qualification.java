package com.sillock.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
public class Qualification {
    @Id
    @Column(name="qualification_id")
    private Long qualificationId;

    @Column(name="member_id")
    private Long memberId;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "is_verified", nullable = true)
    private Boolean isVerified;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "mod_date")
    private String modDate;

    @Column(name = "due_date")
    private String dueDate;

}
