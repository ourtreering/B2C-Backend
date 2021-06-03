package com.sillock.event.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateId;

    @Column(nullable = false, name ="start_date")
    private LocalDateTime startDate;
    @Column(nullable = false, name ="due_date")
    private LocalDateTime dueDate;


    public Boolean isActive(){
    return this.startDate.isBefore(this.dueDate);
    }
}