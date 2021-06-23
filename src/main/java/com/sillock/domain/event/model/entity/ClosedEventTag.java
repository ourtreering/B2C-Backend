package com.sillock.domain.event.model.entity;

import com.sillock.domain.tag.model.entity.Tag;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClosedEventTag {
    @EmbeddedId
    private ClosedEventTagPK id;

    @MapsId("tagId")
    @OneToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @MapsId("closedEventId")
    @OneToOne
    @JoinColumn(name = "closed_event_id")
    private ClosedEvent closedEvent;
}