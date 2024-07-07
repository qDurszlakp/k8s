package com.sandbox.k8s.controller;

import com.sandbox.k8s.dto.AccountDto;
import com.sandbox.k8s.service.DbService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DbApi {

    public final DbService dbService;

    @GetMapping("db/accounts")
    public ResponseEntity<List<AccountDto>> cookies() {
        return ResponseEntity.ok(dbService.getAccounts());
    }

}
