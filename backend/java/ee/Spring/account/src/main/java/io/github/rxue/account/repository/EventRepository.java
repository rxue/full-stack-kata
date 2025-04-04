package io.github.rxue.account.repository;

import io.github.rxue.account.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
