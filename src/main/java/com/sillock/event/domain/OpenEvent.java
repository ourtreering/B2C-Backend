package com.sillock.event.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OpenEvent{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "open_event_id")
    private Long openEventid;

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
}