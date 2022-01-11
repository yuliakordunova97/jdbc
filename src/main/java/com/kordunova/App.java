package com.kordunova;

import com.kordunova.tabels.Department;
import com.kordunova.tabels.Employee;
import com.kordunova.tabels.SalaryGrade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root21";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        getAllItemsFromDepartment();
        getAllItemsFromEmployeeWhereIDBoss3();
        getAllItemsFromEmployeeWhereIBonusIsNotNull();
        getAllItemsFromEmployeeWhereIBonusIsNull();
        getAllItemsWhereSalaryMore3000();
    }

    private static void getAllItemsFromDepartment() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from department");

        List<Department> departmentList = new ArrayList<>();
        while (resultSet.next()){
            Department department = buildDepartment(resultSet);
            departmentList.add(department);
        }
        System.out.println("Depart list: ");
        for (Department d: departmentList) {
            System.out.println(d);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    private static void getAllItemsFromEmployeeWhereIDBoss3() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee where id_boss= 3");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()){
            Employee employee = buildEmployee(resultSet);
            employeeList.add(employee);
        }
        System.out.println();
        System.out.println("Employee with id_boss = 3: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    private static void getAllItemsFromEmployeeWhereIBonusIsNotNull() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee where bonus is not null");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()){
            Employee employee = buildEmployee(resultSet);
            employeeList.add(employee);
        }
        System.out.println();
        System.out.println("Employee with bonus: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    private static void getAllItemsFromEmployeeWhereIBonusIsNull() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee where bonus is null");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()){
            Employee employee = buildEmployee(resultSet);
            employeeList.add(employee);
        }
        System.out.println();
        System.out.println("Employee without bonus: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    private static void getAllItemsWhereSalaryMore3000() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from salary_grade where low_salary > 3000");

        List<SalaryGrade> salary_gradeList = new ArrayList<>();
        while (resultSet.next()){
            SalaryGrade salary_grade = buildSalaryGrade(resultSet);
            salary_gradeList.add(salary_grade);
        }
        System.out.println();
        System.out.println("According to Salary grade, low salary is: ");
        for(SalaryGrade s: salary_gradeList){
            System.out.println(s);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    private static Department buildDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("namee"));
        department.setCity(resultSet.getString("city"));
        return department;
    }
    private static Employee buildEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setLastName(resultSet.getString("lastName"));
        employee.setPosition(resultSet.getString("position"));
        employee.setDateEmployment(resultSet.getDate("date_employment"));
        employee.setIdDepartment(resultSet.getInt("id_department"));
        employee.setIdBoss(resultSet.getInt("id_boss"));
        employee.setRate(resultSet.getInt("rate"));
        employee.setBonus(resultSet.getInt("bonus"));
        return employee;
    }
    private static SalaryGrade buildSalaryGrade(ResultSet resultSet) throws SQLException {
        SalaryGrade salary_grade = new SalaryGrade();
        salary_grade.setId(resultSet.getInt("id"));
        salary_grade.setLowSalary(resultSet.getInt("low_salary"));
        salary_grade.setHightSalary(resultSet.getInt("hight_salary"));

        return salary_grade;
    }
}
