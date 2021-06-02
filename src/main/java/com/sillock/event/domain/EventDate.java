package com.sillock.event.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class EventDate implements Serializable {
    @Column(name ="start_date")
    private LocalDateTime startDate;
    @Column(name ="due_date")
    private LocalDateTime dueDate;

    protected EventDate(){}

    public EventDate(LocalDateTime start,LocalDateTime due) {
        this.startDate = start;
        this.dueDate = due;
    }

    public EventDate 행사등록(LocalDateTime start,LocalDateTime due){
    return new EventDate(start,due);
    }

    public Boolean 행사가_진행중인가(LocalDateTime startDate,LocalDateTime dueDate){
    return startDate.isBefore(dueDate);
    }
}