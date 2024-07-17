package com.sandbox.k8s.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "COUNTRIES")
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID")
    private Long id;

    @Column(name = "COUNTRY")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "INS_TIME")
    private LocalDateTime insertTime;

    @Version
    @Column(name = "VERSION")
    private Integer version;

}
