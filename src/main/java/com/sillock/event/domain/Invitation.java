package com.sillock.event.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
    @EmbeddedId
    private InvitationPK Id;

    private Boolean isApproved;
    private LocalDateTime regDate;
}