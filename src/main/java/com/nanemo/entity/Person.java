package com.nanemo.entity;

import lombok.Data;

@Data
public class Person {
    private Integer personId;
    private String name;
    private String email;
    private Byte age;
    private String address;

}
