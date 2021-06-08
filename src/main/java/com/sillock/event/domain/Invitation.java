package com.sillock.event.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
    @EmbeddedId
    private InvitationPK Id;

    @Column(nullable = false, name = "is_approved")
    private Boolean isApproved;
    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate;
}