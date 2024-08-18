package com.sandbox.k8s.controller.controller;

import com.sandbox.k8s.controller.dto.*;
import com.sandbox.k8s.controller.service.DbService;
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

    @PostMapping("/db/demo")
    public ResponseEntity<Void> demo(@RequestParam("name") String name,
                                     @RequestParam("code") String code,
                                     @RequestParam("accountNumber") String accountNumber) {


        CreateCountryDto country = new CreateCountryDto();
        country.setName(name);
        country.setCode(code);

        CreateAccountDto account = new CreateAccountDto();
        account.setAccountNumber(accountNumber);

        dbService.demo(account, country);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
