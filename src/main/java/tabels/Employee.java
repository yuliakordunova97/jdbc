package tabels;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String position;
    private Date date_employment;
    private int id_department;
    private int id_boss;
    private int rate;
    private int bonus;

    @Override
    public String toString() {
        return "tabels.Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", position='" + position + '\'' +
                ", date_employment=" + date_employment +
                ", id_department=" + id_department +
                ", id_boss=" + id_boss +
                ", rate=" + rate +
                ", bonus=" + bonus +
                '}';
    }
}
