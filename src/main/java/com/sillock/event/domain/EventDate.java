package com.sillock.event.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class EventDate {
    @Column(name ="start_date")
    private LocalDateTime startDate;
    @Column(name ="due_date")
    private LocalDateTime dueDate;

    protected EventDate(){}

    public EventDate(LocalDateTime startDate,LocalDateTime dueDate){
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public Boolean EventIsActive(){
    return this.startDate.isBefore(this.dueDate);
    }
}