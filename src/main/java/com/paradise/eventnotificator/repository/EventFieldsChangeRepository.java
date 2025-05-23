package com.paradise.eventnotificator.repository;

import com.paradise.eventnotificator.domain.entity.EventFieldsChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFieldsChangeRepository extends JpaRepository<EventFieldsChange, Long> {
}
