package com.sillock.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long eventId;

    @Column(name="image", length = 255)
    private String image;

    @Column(name="count_limit")
    private Long countLimit;

    @Column(name="name", length = 30)
    private String name;

    @Column(name = "is_exchangeable", nullable = false)
    private Boolean isExchangeable;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name="qr_image", length = 255)
    private String qrImage;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

}
