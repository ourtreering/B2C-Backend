package com.sillock.event.entity;

import com.sillock.domain.tag.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@AllArgsConstructor
public class CalculateTagEvent {
    private ObjectId memberId;
    private List<Tag> tagList;
}
