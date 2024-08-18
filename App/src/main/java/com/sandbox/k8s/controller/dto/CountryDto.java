package com.sandbox.k8s.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CountryDto {
    private Long id;
    private String name;
    private String code;
    private LocalDateTime insertTime;
    private Integer version;
}
