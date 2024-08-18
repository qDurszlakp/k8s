package com.sandbox.k8s.controller.dto;

import com.sandbox.k8s.controller.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    private Long id;
    private Integer version;
    private String cardNumber;
    private Account account;
}
