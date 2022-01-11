package com.kordunova.tabels;

import lombok.Data;

@Data
public class SalaryGrade {
    private int id;
    private int low_salary;
    private int hight_salary;

    @Override
    public String toString() {
        return "tabels.Salary_Grade{" +
                "id=" + id +
                ", low_salary=" + low_salary +
                ", hight_salary=" + hight_salary +
                '}';
    }
}
