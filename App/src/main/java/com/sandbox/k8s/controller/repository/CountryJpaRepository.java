package com.sandbox.k8s.controller.repository;

import com.sandbox.k8s.controller.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryJpaRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryByCode(String code);

    Country save(Country entity);
}
