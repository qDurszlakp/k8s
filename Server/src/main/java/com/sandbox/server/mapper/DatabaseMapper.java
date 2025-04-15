package com.sandbox.server.mapper;

import com.sandbox.server.dto.*;
import com.sandbox.server.entity.Account;
import com.sandbox.server.entity.Card;
import com.sandbox.server.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DatabaseMapper {

    CountryDto countryToCountryDto(Country country);

    AccountDto accountToAccountDto(Account account);

    @Mapping(source = "account.accountNumber", target = "accountNumber")
    CardDto cardToCardDto(Card card);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTime", ignore = true)
    @Mapping(target = "version", ignore = true)
    Country countryDtoToCountry(CreateCountryDto createCountryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "cards", ignore = true)
    Account accountDtoToAccount(CreateAccountDto createCountryDto);

}

