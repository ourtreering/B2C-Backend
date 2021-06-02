package com.sillock.event.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.time.LocalDateTime;

@Entity
@Inheritance
public class Event {
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @Column(name="image")
    private String image;
    @Column(name="regDate")
    private LocalDateTime regDate;
    @Column(name="modDate")
    private LocalDateTime modDate;

    @Embedded
    private EventDate date;
}