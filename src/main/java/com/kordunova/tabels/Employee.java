package com.kordunova.tabels;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private Date dateEmployment;
    private int idDepartment;
    private int idBoss;
    private int rate;
    private int bonus;
}
