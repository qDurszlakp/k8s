package com.sandbox.k8s.controller.dto;

import lombok.Getter;

@Getter
public class PostDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
