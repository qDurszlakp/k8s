package com.sandbox.k8s.repository;

import com.sandbox.k8s.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Long> {
}
