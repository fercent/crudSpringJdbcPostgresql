package com.spring.testtask.java.service;

import com.spring.testtask.java.dao.EmployeeDao;
import com.spring.testtask.java.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void createEmployee(Employee employee) {
        employeeDao.createEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public List<Employee> listEmployee() {
        return employeeDao.listEmployee();
    }

    @Override
    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public void updateEmployee(long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);
    }

    @Override
    public boolean existsEmployee(long id) {
        return employeeDao.existEmployee(id);
    }
}
