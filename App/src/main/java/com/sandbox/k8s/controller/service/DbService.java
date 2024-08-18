package com.sandbox.k8s.controller.service;

import com.sandbox.k8s.controller.dto.*;
import com.sandbox.k8s.controller.entity.Account;
import com.sandbox.k8s.controller.entity.Country;
import com.sandbox.k8s.controller.mapper.DbMapper;
import com.sandbox.k8s.controller.repository.AccountJpaRepository;
import com.sandbox.k8s.controller.repository.CardJpaRepository;
import com.sandbox.k8s.controller.repository.CountryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        return null;
    }

    public CountryDto createCountry(CreateCountryDto countryDto) {
        Country entryCountry = mapper.countryDtoToCountry(countryDto);
        entryCountry.setInsertTime(ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        Country savedEntity = countryJpaRepository.save(entryCountry);
        return mapper.countryToCountryDto(savedEntity);
    }

    public AccountDto createAccount(CreateAccountDto accountDto) {
        Account savedEntity = accountJpaRepository.save(mapper.accountDtoToAccount(accountDto));
        return mapper.accountToAccountDto(savedEntity);
    }


    @Transactional
    public void demo(CreateAccountDto accountDto, CreateCountryDto countryDto) {
        createCountry(countryDto);
        createAccount(accountDto);
    }
}
