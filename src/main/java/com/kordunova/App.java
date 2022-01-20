package com.kordunova;

import com.kordunova.tabels.Department;
import com.kordunova.tabels.Employee;
import com.kordunova.tabels.SalaryGrade;
import org.w3c.dom.ls.LSException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root21";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        List<Department> departmentList = getAllItemsFromDepartment();
        System.out.println("Depart list: ");
        for (Department d : departmentList) {
            System.out.println(d);
        }

        List<Employee> employeeList = getAllItemsFromEmployeeWhereIDBoss3();
        System.out.println();
        System.out.println("Employee with id_boss = 3: ");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        List<Employee> employeeList2 = getAllItemsFromEmployeeWhereIBonusIsNotNull();
        System.out.println();
        System.out.println("Employee with bonus: ");
        for (Employee employee : employeeList2) {
            System.out.println(employee);
        }

        List<Employee> employeeList3 = getAllItemsFromEmployeeWhereIBonusIsNull();
        System.out.println();
        System.out.println("Employee without bonus: ");
        for (Employee employee : employeeList3) {
            System.out.println(employee);
        }

        List<SalaryGrade> salaryGradeList = getAllItemsWhereSalaryMore3000();
        System.out.println();
        System.out.println("According to Salary grade, low salary is: ");
        for (SalaryGrade s : salaryGradeList) {
            System.out.println(s);
        }

    }

    private static List<Department> getAllItemsFromDepartment() {
        List<Department> departmentList = new ArrayList<>();
        try (
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
        ){
            String sql = "select id, namee,city from department";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("namee"));
                department.setCity(resultSet.getString("city"));
                departmentList.add(department);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    private static List<Employee> getAllItemsFromEmployeeWhereIDBoss3() {
        List<Employee> employees = new ArrayList<>();
        try ( Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
              Statement statement = connection.createStatement();
        ){

            String sql = "select id, firstname, lastname,position, date_employment,"+
                    " id_department, id_boss, rate, bonus from employee where id_boss= 3";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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
                employees.add(employee);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static List<Employee> getAllItemsFromEmployeeWhereIBonusIsNotNull() {
        List<Employee> employees = new ArrayList<>();
        try (
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
        ){
            String sql = "select id, firstname, lastname,position, date_employment,"+
                    " id_department, id_boss, rate, bonus"+
                    " from employee where bonus is not null";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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
                employees.add(employee);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static List<Employee> getAllItemsFromEmployeeWhereIBonusIsNull() {

        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ){

            String sql = "select id, firstname, lastname,position, date_employment," +
                    " id_department, id_boss, rate, bonus from employee where bonus is null";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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
                employees.add(employee);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static List<SalaryGrade> getAllItemsWhereSalaryMore3000() {
        List<SalaryGrade> salaryGradeList = new ArrayList<>();
        try ( Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
              Statement statement = connection.createStatement();
              ){

            String sql = "select id, low_salary, high_salary from salary_grade where low_salary > 3000";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                SalaryGrade salaryGrade = new SalaryGrade();
                salaryGrade.setId(resultSet.getInt("id"));
                salaryGrade.setLowSalary(resultSet.getInt("low_salary"));
                salaryGrade.setHighSalary(resultSet.getInt("high_salary"));
                salaryGradeList.add(salaryGrade);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaryGradeList;
    }
}
