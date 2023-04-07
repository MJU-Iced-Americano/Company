package com.mju.course.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

    public Company(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
