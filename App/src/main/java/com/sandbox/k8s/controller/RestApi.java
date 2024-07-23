package com.sandbox.k8s.controller;

import com.sandbox.k8s.acpect.ThreadLog;
import com.sandbox.k8s.client.PostsWebClient;
import com.sandbox.k8s.dto.PostDto;
import com.sandbox.k8s.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestApi {

    private final FileService fileService;
    private final PostsWebClient postsWebClient;

    @GetMapping("/cookies")
    public ResponseEntity<String> cookies() {

        val cookies = """
                    Cookie | 1
                    Cookie | 2
                    Cookie | 3
                    Cookie | 4
                    Cookie | 5
                """;

        return ResponseEntity.ok(cookies);
    }

    @PostMapping("/file/{path}")
    public ResponseEntity<Void> cookies(@PathVariable("path") String path) {
        fileService.save(path);
        return ResponseEntity.ok().build();
    }

    @ThreadLog
    @GetMapping("/tmpData")
    public ResponseEntity<List<PostDto>> posts() {
        val posts = postsWebClient.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/tmp")
    public ResponseEntity<String> tmp() {
        val pass = postsWebClient.getPassphrase();
        return ResponseEntity.ok(pass);
    }
}
