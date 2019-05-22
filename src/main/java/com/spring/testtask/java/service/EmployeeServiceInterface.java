package com.spring.testtask.java.service;

import com.spring.testtask.java.dto.Employee;

import java.util.List;

public interface EmployeeServiceInterface {

    void createEmployee(Employee employee);

    Employee getEmployeeById(long id);

    List<Employee> listEmployee();

    void deleteEmployee(long id);

    void updateEmployee(long id, Employee employee);

    boolean existsEmployee(long id);
}
