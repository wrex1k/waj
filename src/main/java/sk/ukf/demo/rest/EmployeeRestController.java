package sk.ukf.demo.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(employees, "Employees sucesfully listed."));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> findByID(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(employee, "Employee sucesfully listed."));
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(0);
        Employee updatedEmployee = employeeService.save(employee);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(updatedEmployee, "Employee sucesfully added."));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(updatedEmployee, "Employee succesfully updated."));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> deleteEmployee (@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        employeeService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(employee, "Employee succesfully deleted."));
    }

    @GetMapping("/employeesSearch")
    public ResponseEntity<ApiResponse<List<Employee>>> search(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        List<Employee> results = employeeService.findByFirstNameAndLastName(firstName, lastName);

        return ResponseEntity.ok(ApiResponse.success(results, "Search completed successfully."));
    }
}