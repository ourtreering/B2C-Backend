package com.sillock.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id", nullable = false)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "myActivity_id")
    @Column(name="myActivity_id", nullable = false)
    private Long myActivityId;

    @Column(name="image", length = 255, nullable = false)
    private String image;

    @Column(name="title", length = 255, nullable = false)
    private String title;

    @Column(name="host", length = 255, nullable = false)
    private String host;

    @Column(name="content", length = 255, nullable = false)
    private String content;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;


}
