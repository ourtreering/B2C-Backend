package com.sillock.event.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InvitationPK implements Serializable {
    private Long memberId;
    private Long closedEventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvitationPK)) return false;
        InvitationPK that = (InvitationPK) o;
        return memberId.equals(that.memberId) && closedEventId.equals(that.closedEventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, closedEventId);
    }
}