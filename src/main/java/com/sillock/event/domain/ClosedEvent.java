package com.sillock.event.domain;

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
public class ClosedEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "closed_event_id")
    private Long closedEventId;

    @Column(nullable = false, name="title")
    private String title;
    @Column(nullable = false, name = "host")
    private String host;
    @Column(nullable = false,name="content")
    private String content;
    @Column(nullable = true,name="image")
    private String image;
    @Column(nullable = false,name="regDate")
    private LocalDateTime regDate;
    @Column(nullable = false,name="modDate")
    private LocalDateTime modDate;
    @Column(nullable = false,name="search_keyword")
    private String searchKeyword;


}