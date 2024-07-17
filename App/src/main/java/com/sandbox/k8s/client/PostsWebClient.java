package com.sandbox.k8s.client;

import com.sandbox.k8s.acpect.ThreadLog;
import com.sandbox.k8s.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostsWebClient {

    @Value("${data.exampleUri}")
    private String uri;

    @Value("${data.passphraseUrl}")
    private String passphraseUrl;

    @ThreadLog
    public List<PostDto> getAllPosts() {
        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PostDto>>() {})
                .block();
    }

    public String getPassphrase() {
        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(passphraseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
