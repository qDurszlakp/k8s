package com.sandbox.server.repository;

import com.sandbox.server.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Long> {
}
