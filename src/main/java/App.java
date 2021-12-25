import tabels.Department;
import tabels.Employee;
import tabels.Salary_Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root21";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        Statement statement5 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("select * from department");
        ResultSet resultSet2 = statement2.executeQuery("select * from employee where id_boss= 3");
        ResultSet resultSet3 = statement3.executeQuery("select * from employee where bonus is not null");
        ResultSet resultSet4 = statement4.executeQuery("select * from employee where bonus is null");
        ResultSet resultSet5 = statement5.executeQuery("select * from salary_grade where low_salary > 3000");


        List<Department> departmentList = new ArrayList<>();
        while (resultSet1.next()){
            Department department = buildDepartment(resultSet1);
            departmentList.add(department);
        }
        System.out.println("Depart list: ");
        for (Department d: departmentList) {
            System.out.println(d);
        }

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet2.next()){
            Employee employee = buildEmployee(resultSet2);
            employeeList.add(employee);
        }
        System.out.println();
        System.out.println("Employee with id_boss = 3: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        while (resultSet3.next()){
            Employee employee = buildEmployee(resultSet3);
            employeeList.add(employee);
        }
        System.out.println();
        System.out.println("Employee with bonus: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        while (resultSet4.next()){
            Employee employee = buildEmployee(resultSet4);
            employeeList.add(employee);
        }
        System.out.println();
        System.out.println("Employee without bonus: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        List<Salary_Grade> salary_gradeList = new ArrayList<>();
        while (resultSet5.next()){
            Salary_Grade salary_grade = buildSalary_grade(resultSet5);
            salary_gradeList.add(salary_grade);
        }
        System.out.println();
        System.out.println("According to Salary grade, low salary is: ");
        for(Salary_Grade s: salary_gradeList){
            System.out.println(s);
        }

        resultSet1.close();
        statement1.close();

        resultSet2.close();
        statement2.close();

        resultSet3.close();
        statement3.close();

        resultSet4.close();
        statement4.close();

        resultSet5.close();
        statement5.close();

        connection.close();
    }


    private static Department buildDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt(1));
        department.setName(resultSet.getString(2));
        department.setCity(resultSet.getString(3));
        return department;
    }
    private static Employee buildEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt(1));
        employee.setFirstname(resultSet.getString(2));
        employee.setLastname(resultSet.getString(3));
        employee.setPosition(resultSet.getString(4));
        employee.setDate_employment(resultSet.getDate(5));
        employee.setId_department(resultSet.getInt(6));
        employee.setId_boss(resultSet.getInt(7));
        employee.setRate(resultSet.getInt(8));
        employee.setBonus(resultSet.getInt(9));
        return employee;
    }
    private static Salary_Grade buildSalary_grade(ResultSet resultSet) throws SQLException {
        Salary_Grade salary_grade = new Salary_Grade();
        salary_grade.setId(resultSet.getInt(1));
        salary_grade.setLow_salary(resultSet.getInt(2));
        salary_grade.setHight_salary(resultSet.getInt(3));

        return salary_grade;
    }
}
