package com.nanemo.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Person {
    private Integer personId;
    private String name;
    private String email;
    private Byte age;
    private String address;

    public Person(String name, String email, Byte age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }
}
