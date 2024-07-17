package com.sandbox.k8s.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController("/")
@RequiredArgsConstructor
public class RestApi {


    @GetMapping()
    public ResponseEntity<String> cookies() {

        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        val random = new SecureRandom();

        val sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            val index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return ResponseEntity.ok(sb.toString().toUpperCase());
    }

}
