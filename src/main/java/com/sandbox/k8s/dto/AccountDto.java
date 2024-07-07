package com.sandbox.k8s.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private Long id;
    private Integer version;
    private String accountNumber;

}
