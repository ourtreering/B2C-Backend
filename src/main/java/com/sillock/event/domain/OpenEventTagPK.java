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
public class OpenEventTagPK implements Serializable {
    private Long tagId;
    private Long openEventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpenEventTagPK)) return false;
        OpenEventTagPK that = (OpenEventTagPK) o;
        return getTagId().equals(that.getTagId()) && getOpenEventId().equals(that.getOpenEventId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTagId(), getOpenEventId());
    }
}