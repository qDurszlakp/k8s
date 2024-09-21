package com.sandbox.k8s.controller.controller;

import com.sandbox.k8s.controller.aspect.ThreadLog;
import com.sandbox.k8s.controller.client.PostsWebClient;
import com.sandbox.k8s.controller.dto.PostDto;
import com.sandbox.k8s.controller.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class RestApi {

    private final FileService fileService;
    private final PostsWebClient postsWebClient;

    @GetMapping(value = "/cookies", produces = "application/json")
    public ResponseEntity<String> cookies() {

        val cookies = """
            [
                {"name": "Cookie", "value": 1},
                {"name": "Cookie", "value": 2},
                {"name": "Cookie", "value": 3},
                {"name": "Cookie", "value": 4},
                {"name": "Cookie", "value": 5}
            ]
               \s""";

        return ResponseEntity.ok(cookies);
    }

    @PostMapping("/file/{content}")
    public ResponseEntity<Void> file(@PathVariable("content") String content) {
        fileService.save(content);
        return ResponseEntity.ok().build();
    }

    @ThreadLog
    @GetMapping("/tmpData")
    public ResponseEntity<List<PostDto>> posts() {
        val posts = postsWebClient.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/passphrase")
    public ResponseEntity<String> tmp() {
        val pass = postsWebClient.getPassphrase();
        return ResponseEntity.ok(pass);
    }
}
