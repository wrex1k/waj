package sk.ukf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
    }