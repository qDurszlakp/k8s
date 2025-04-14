package com.sandbox.k8s.controller.repository;

import com.sandbox.k8s.controller.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

}
