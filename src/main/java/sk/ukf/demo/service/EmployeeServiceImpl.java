package sk.ukf.demo.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.demo.dao.EmployeeDAO;
import sk.ukf.demo.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
        return employeeDAO.findByFirstNameAndLastName(firstName, lastName);
    }
}
