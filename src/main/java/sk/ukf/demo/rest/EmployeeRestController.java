package sk.ukf.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findEmployeeById(@PathVariable int id) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        if (employeeService.findById(id) == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        employee.setId(id);
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if (employeeService.findById(id) == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        employeeService.deleteById(id);
        return "Deleted student id - " + id;
    }

    @GetMapping("/employeeSearch")
    public List<Employee> search(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findByFirstNameAndLastName(firstName, lastName);
    }
}