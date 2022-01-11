package com.kordunova.tabels;

import lombok.Data;

@Data
public class Department {
    private int id;
    private String name;
    private String city;

    @Override
    public String toString() {
        return "tabels.Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
