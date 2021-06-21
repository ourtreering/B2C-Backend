package com.sillock.event.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class ClosedEventDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "closed_event_id")
    private ClosedEvent closedEvent;

    @Column(nullable = false, name ="start_date")
    private LocalDateTime startDate;
    @Column(nullable = false, name ="due_date")
    private LocalDateTime dueDate;


    public Boolean isActive(){
        return this.startDate.isBefore(this.dueDate);
    }
}