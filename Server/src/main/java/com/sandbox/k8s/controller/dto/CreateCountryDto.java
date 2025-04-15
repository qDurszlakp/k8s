package com.sandbox.k8s.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCountryDto {
    private String name;
    private String code;
}
