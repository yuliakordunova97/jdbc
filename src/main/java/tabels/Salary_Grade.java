package tabels;

import lombok.Data;

@Data
public class Salary_Grade {
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
