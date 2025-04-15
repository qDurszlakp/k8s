package com.sandbox.k8s.repository;

import com.sandbox.k8s.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditJpaRepository extends JpaRepository<Audit, Long> {
}
