package com.sandbox.k8s.controller.repository;

import com.sandbox.k8s.controller.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Long> {
}
