package com.sandbox.k8s.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCountryDto {
    private String name;
    private String code;
}
