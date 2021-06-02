package com.sillock.event.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.time.LocalDateTime;

@Entity
@Inheritance
public class Event {
    private String title;
    @Embedded
    private Host host;
    private String content;
    private String image;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    @Embedded
    private EventDate date;
}