package com.sillock.domain.event.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OpenEventDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "open_event_id")
    private OpenEvent openEvent;

    @Column(nullable = false, name ="start_date")
    private LocalDateTime startDate;
    @Column(nullable = false, name ="due_date")
    private LocalDateTime dueDate;


    public Boolean isActive(){
        return this.startDate.isBefore(this.dueDate);
    }
}