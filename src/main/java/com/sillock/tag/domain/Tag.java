package com.sillock.tag.domain;

import javax.persistence.Id;
import java.time.LocalDateTime;

public class Tag {
    @Id
    private Long id;
    private String name;
    private LocalDateTime regDate;
}