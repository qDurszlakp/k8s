package com.sandbox.k8s;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequiredArgsConstructor
public class RestApi {

    private final FileService fileService;

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

    @PostMapping("cookies")
    public ResponseEntity<Void> fooPost(@RequestParam String content) {
        fileService.save(content);
        return ResponseEntity.ok().build();
    }

}
