package com.sillock.event.infra.repository;

import com.sillock.event.domain.OpenEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenEventRepository extends JpaRepository<OpenEvent, Long> {

}
