package com.sillock.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Qualification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="qualification_id", nullable = false)
    private Long qualificationId;

    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified=false;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate=LocalDateTime.now();

    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate= LocalDateTime.now();

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

}
