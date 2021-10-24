package com.sillock.domain.sillog.model.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Qna {
    private String question;
    private String answer;
}
