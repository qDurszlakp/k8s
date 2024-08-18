package com.sandbox.k8s.controller.repository;

import com.sandbox.k8s.controller.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditJpaRepository extends JpaRepository<Audit, Long> {
}
