package sk.ukf.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.demo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}