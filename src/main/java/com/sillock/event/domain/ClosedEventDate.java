package com.sillock.event.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name ="CLOSED_EVENT_DATE")
public class ClosedEventDate extends EventDate{
    @ManyToOne
    @JoinColumn(nullable = false, name = "OPEN_EVENT")
    private Long closedEventId;
}