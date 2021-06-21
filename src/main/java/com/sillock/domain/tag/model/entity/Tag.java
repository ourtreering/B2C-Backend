package com.sillock.domain.tag.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@Entity
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "tag_id")
    private Long tagId;

    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate;
}