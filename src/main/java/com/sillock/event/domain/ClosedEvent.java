package com.sillock.event.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ClosedEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "closed_event_id")
    private Long closedEventId;

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
    @Column(name="search_keyword")
    private String searchKeyword;

    @Embedded
    private Host host;

}