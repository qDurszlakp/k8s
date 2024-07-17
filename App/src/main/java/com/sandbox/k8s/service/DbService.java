package com.sandbox.k8s.service;

import com.sandbox.k8s.dto.AccountDto;
import com.sandbox.k8s.dto.CardDto;
import com.sandbox.k8s.dto.CountryDto;
import com.sandbox.k8s.mapper.DbMapper;
import com.sandbox.k8s.repository.AccountJpaRepository;
import com.sandbox.k8s.repository.CardJpaRepository;
import com.sandbox.k8s.repository.CountryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DbService {

    private final DbMapper mapper;
    private final AccountJpaRepository accountJpaRepository;
    private final CardJpaRepository accountCardJpaRepository;
    private final CountryJpaRepository countryJpaRepository;

    public List<AccountDto> getAccounts() {
        return accountJpaRepository.findAll()
                .stream().map(mapper::accountToAccountDto)
                .toList();
    }

    public List<CardDto> getCards() {
        return accountCardJpaRepository.findAll()
                .stream().map(mapper::cardToCardDto)
                .toList();
    }

    public List<CountryDto> getCountries() {
        return countryJpaRepository.findAll()
                .stream().map(mapper::countryToCountryDto)
                .toList();
    }
}
