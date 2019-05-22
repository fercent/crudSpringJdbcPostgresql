package com.spring.testtask.java.dao;

import com.spring.testtask.java.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao extends JdbcDaoSupport implements EmployeeDaoInterace {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void createEmployee(Employee emp) {
        String sql = "INSERT INTO employee "+
                "(employee_id, first_name, last_name, department_id, job_title, gender, birthday)"+
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{
                emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(), emp.getDepartment_id(),
                emp.getJobTitle(), emp.getGender().toString(), emp.getDateOfBirth()
        });
    }

    @Override
    public Employee getEmployeeById(long id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        return (Employee)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rwNumber) throws SQLException{
                if(rs.wasNull())
                    return null;

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getLong("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setDepartment_id(rs.getLong("department_id"));
                employee.setJobTitle(rs.getString("job_title"));
                employee.setGender(rs.getString("gender"));
                employee.setDateOfBirth(rs.getString("birthday"));
                return employee;
            }
        });
    }

    @Override
    public boolean existEmployee(long id){
        String sql = "SELECT EXISTS(SELECT employee_id FROM employee WHERE employee_id = ?)";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, Boolean.class);
    }

    @Override
    public List<Employee> listEmployee() {
        String sql = "SELECT * FROM employee";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Employee> result = new ArrayList<Employee>();


        for(Map<String, Object> row:rows){
            Employee employee = new Employee();
            employee.setEmployeeId((Long)row.get("employee_id"));
            employee.setFirstName((String)row.get("first_name"));
            employee.setLastName((String)row.get("last_name"));
            employee.setDepartment_id((Long)row.get("department_id"));
            employee.setJobTitle((String)row.get("job_title"));
            employee.setGender((String)row.get("gender"));
            employee.setDateOfBirth((String)row.get("birthday"));
            result.add(employee);
        }
        return result;
    }

    @Override
    public void deleteEmployee(long id) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        getJdbcTemplate().update(sql, id);
        System.out.println("Employee with id: " + id + " successfully removed");

    }

    @Override
    public void updateEmployee(long id, Employee employee) {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, " +
                "job_title = ?, gender = ?, birthday = ? WHERE employee_id = ?";

        getJdbcTemplate().update(sql, employee.getFirstName(), employee.getLastName(), employee.getDepartment_id(),
                employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth(), id);

        System.out.println("Employee with id: " + id + " successfully updated.");

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