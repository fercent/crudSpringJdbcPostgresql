package com.spring.testtask.java.dao;

import com.spring.testtask.java.dto.Employee;

import java.util.List;

public interface EmployeeDaoInterace {

    void createEmployee(Employee employee);

    Employee getEmployeeById(long id);

    List<Employee> listEmployee();

    void deleteEmployee(long id);

    void updateEmployee(long id, Employee employee);

    boolean existEmployee(long id);
}
