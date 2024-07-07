package com.sandbox.k8s.mapper;

import com.sandbox.k8s.dto.AccountDto;
import com.sandbox.k8s.dto.CardDto;
import com.sandbox.k8s.dto.CountryDto;
import com.sandbox.k8s.entity.Account;
import com.sandbox.k8s.entity.Card;
import com.sandbox.k8s.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DbMapper {

    CountryDto countryToCountryDto(Country country);

    AccountDto accountToAccountDto(Account account);

    CardDto cardToCardDto(Card card);

}
