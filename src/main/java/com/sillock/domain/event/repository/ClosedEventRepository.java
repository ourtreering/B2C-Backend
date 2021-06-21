package com.sillock.domain.event.repository;


import com.sillock.domain.event.model.entity.ClosedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosedEventRepository extends JpaRepository<ClosedEvent,Long> {
}
