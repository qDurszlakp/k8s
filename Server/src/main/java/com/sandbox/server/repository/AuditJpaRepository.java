package com.sandbox.server.repository;

import com.sandbox.server.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditJpaRepository extends JpaRepository<Audit, Long> {
}
