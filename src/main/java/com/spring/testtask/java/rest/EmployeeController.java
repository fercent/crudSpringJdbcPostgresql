package com.spring.testtask.java.rest;

import com.spring.testtask.java.dto.Employee;
import com.spring.testtask.java.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployee() {
        List<Employee> employees = employeeService.listEmployee();
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }


    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching employee with id " + id);
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }





    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestParam(value = "id") long id,
                                               @RequestParam(value = "firstName") String firstName,
                                               @RequestParam(value = "lastName") String lastName,
                                               @RequestParam(value = "departmentId") long departmentId,
                                               @RequestParam(value = "jobTitle") String jobTitle,
                                               @RequestParam(value = "gender") String gender,
                                               @RequestParam(value = "dateOfTheBirth") String dateOfTheBirth,
                                               UriComponentsBuilder ucBuilder) {

        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment_id(departmentId);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfTheBirth);
        System.out.println("Creating Employee " + employee.getFirstName()+" "+employee.getLastName());


        if (employeeService.existsEmployee(id)) {
            System.out.println("A Employee with name " + employee.getFirstName() + " " + employee.getLastName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        employeeService.createEmployee(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }





    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestParam(value = "firstName") String firstName,
                                                   @RequestParam(value = "lastName") String lastName,
                                                   @RequestParam(value = "departmentId") long departmentId,
                                                   @RequestParam(value = "jobTitle") String jobTitle,
                                                   @RequestParam(value = "gender") String gender,
                                                   @RequestParam(value = "dateOfTheBirth") String dateOfTheBirth) {
        System.out.println("Updating Employee " + id);

        Employee currentEmployee = employeeService.getEmployeeById(id);

        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment_id(departmentId);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfTheBirth);

        if (currentEmployee==null) {
            System.out.println("Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setDepartment_id(employee.getDepartment_id());
        currentEmployee.setJobTitle(employee.getJobTitle());
        currentEmployee.setGender(employee.getGender());
        currentEmployee.setDateOfBirth(employee.getDateOfBirth());

        employeeService.updateEmployee(currentEmployee.getEmployeeId(), currentEmployee);
        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }





    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Employee with id " + id);

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            System.out.println("Unable to delete. Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        employeeService.deleteEmployee(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}
