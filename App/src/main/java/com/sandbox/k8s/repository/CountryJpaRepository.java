package com.sandbox.k8s.repository;

import com.sandbox.k8s.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryJpaRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryByCode(String code);

}
