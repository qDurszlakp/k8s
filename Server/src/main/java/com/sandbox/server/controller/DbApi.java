package com.sandbox.server.controller;

import com.sandbox.server.dto.*;
import com.sandbox.server.service.DbService;
import com.sandbox.server.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DbApi {

    public final DbService dbService;

    @GetMapping("/db/accounts")
    public ResponseEntity<List<AccountDto>> accounts() {
        return ResponseEntity.ok(dbService.getAccounts());
    }

    @GetMapping("/db/cards")
    public ResponseEntity<List<CardDto>> cards() {
        return ResponseEntity.ok(dbService.getCards());
    }

    @PostMapping("/db/country")
    public ResponseEntity<CountryDto> createCountry(@RequestBody CreateCountryDto country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dbService.createCountry(country));
    }

    @PostMapping("/db/account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dbService.createAccount(account));
    }

}
