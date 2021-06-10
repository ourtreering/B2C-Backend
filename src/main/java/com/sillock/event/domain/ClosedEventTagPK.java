package com.sillock.event.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@Embeddable
public class ClosedEventTagPK implements Serializable {
    private Long tagId;
    private Long closedEventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClosedEventTagPK)) return false;
        ClosedEventTagPK that = (ClosedEventTagPK) o;
        return tagId.equals(that.tagId) && closedEventId.equals(that.closedEventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, closedEventId);
    }
}