package sk.ukf.demo.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.demo.repository.EmployeeRepository;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.exception.ObjectNotFoundException;
import sk.ukf.demo.exception.EmailAlreadyExistsException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Employee", id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == 0) {
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        } else {
            Employee existingWithEmail = employeeRepository.findByEmail(employee.getEmail()).orElse(null);
            if (existingWithEmail != null && existingWithEmail.getId() != employee.getId()) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        }

        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Employee", id);
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
        if (!employeeRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new ObjectNotFoundException("Employee", firstName, lastName);
        }

        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
