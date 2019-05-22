package com.spring.testtask.java.dto;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private long department_id;
    private String jobTitle;
    private Gender gender;
    private String dateOfBirth;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return " Worker { ID= " + employeeId + " Name= "+firstName +" "+ lastName + " department= " + department_id +
                " job title= " +  jobTitle + " gender= " + gender + " Birthday= " + dateOfBirth;
    }
}
/*
id
first name
last name
department id
job title
gender
date of birth
 */