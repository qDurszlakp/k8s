package com.sandbox.k8s.repository;

import com.sandbox.k8s.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

}
