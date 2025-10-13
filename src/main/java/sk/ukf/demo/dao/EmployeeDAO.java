package sk.ukf.demo.dao;

import sk.ukf.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}