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
    @Column(name="event_id", nullable = false)
    private Long eventId;

    @Column(name="image", length = 255, nullable = false)
    private String image;

    @Column(name="count_limit", nullable = false)
    private Long countLimit;

    @Column(name="name", length = 30, nullable = false)
    private String name;

    @Column(name = "is_exchangeable", nullable = false)
    private Boolean isExchangeable=false;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Column(name="qr_image", length = 255, nullable = true)
    private String qrImage;

    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate = LocalDateTime.now();

}
