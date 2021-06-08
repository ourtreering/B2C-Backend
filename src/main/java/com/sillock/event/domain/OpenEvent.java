package com.sillock.event.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class OpenEvent{
    @Id
    private Long openEventid;

    @Column(nullable = false, name="title")
    private String title;
    @Column(nullable = false,name="content")
    private String content;
    @Column(nullable = true,name="image")
    private String image;
    @Column(nullable = false,name="regDate")
    private LocalDateTime regDate;
    @Column(nullable = false,name="modDate")
    private LocalDateTime modDate;

    @Embedded
    private Host host;
}