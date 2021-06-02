package com.sillock.comment.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long commentId;
    private Long memberId;
    private String comment;
    private String image;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
