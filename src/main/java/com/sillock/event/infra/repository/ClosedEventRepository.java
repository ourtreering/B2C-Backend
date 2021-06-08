package com.sillock.event.infra.repository;


import com.sillock.event.domain.ClosedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosedEventRepository extends JpaRepository<ClosedEvent,Long> {
}
