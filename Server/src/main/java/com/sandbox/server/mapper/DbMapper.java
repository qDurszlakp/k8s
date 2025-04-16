package com.sandbox.server.mapper;

import com.sandbox.server.dto.*;
import com.sandbox.server.dto.*;
import com.sandbox.server.entity.Account;
import com.sandbox.server.entity.Card;
import com.sandbox.server.entity.Country;
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
