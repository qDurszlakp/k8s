package com.sandbox.k8s.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private Long id;
    private Integer version;
    private String accountNumber;

}
