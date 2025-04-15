package com.sandbox.k8s.mapper;

import com.sandbox.k8s.entity.Account;
import com.sandbox.k8s.entity.Card;
import com.sandbox.k8s.entity.Country;
import com.sandbox.k8s.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DbMapper {

    CountryDto countryToCountryDto(Country country);

    AccountDto accountToAccountDto(Account account);

    @Mapping(source = "account.accountNumber", target = "accountNumber")
    CardDto cardToCardDto(Card card);

    Country countryDtoToCountry(CreateCountryDto createCountryDto);

    Account accountDtoToAccount(CreateAccountDto createCountryDto);
}
