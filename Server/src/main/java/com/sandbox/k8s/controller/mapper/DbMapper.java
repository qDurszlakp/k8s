package com.sandbox.k8s.controller.mapper;

import com.sandbox.k8s.controller.dto.*;
import com.sandbox.k8s.controller.entity.Account;
import com.sandbox.k8s.controller.entity.Card;
import com.sandbox.k8s.controller.entity.Country;
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
