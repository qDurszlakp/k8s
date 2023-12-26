package com.sandbox.k8s;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class RestApi {

    @GetMapping("cookies")
    public ResponseEntity<String> fooGet() {

        val cookies = """
                    Cookie | 1
                    Cookie | 2
                    Cookie | 3
                    Cookie | 4
                """;

        return ResponseEntity.ok(cookies);
    }

}
