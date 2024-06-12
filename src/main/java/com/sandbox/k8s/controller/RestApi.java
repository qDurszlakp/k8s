package com.sandbox.k8s.controller;

import com.sandbox.k8s.ThreadLog;
import com.sandbox.k8s.client.PostsWebClient;
import com.sandbox.k8s.dto.PostDto;
import com.sandbox.k8s.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class RestApi {

    private final FileService fileService;
    private final PostsWebClient postsWebClient;

    @GetMapping("cookies")
    public ResponseEntity<String> cookies() {

        val cookies = """
                    Cookie | 1
                    Cookie | 2
                    Cookie | 3
                    Cookie | 4
                """;

        return ResponseEntity.ok(cookies);
    }

    @PostMapping("cookies")
    public ResponseEntity<Void> cookies(@RequestParam String content) {
        fileService.save(content);
        return ResponseEntity.ok().build();
    }

    @ThreadLog
    @GetMapping("tmpData")
    public ResponseEntity<List<PostDto>> posts() {
        val posts = postsWebClient.getAllPosts();
        return ResponseEntity.ok(posts);
    }

}
