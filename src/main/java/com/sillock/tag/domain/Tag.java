package com.sillock.tag.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "tag_id")
    private Long tagId;

    @Column(nullable = false, name = "name")
    private String name;

    @Builder.Default
    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now();
}