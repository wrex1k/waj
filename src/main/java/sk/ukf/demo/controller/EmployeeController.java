package sk.ukf.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.entity.Employee;
import sk.ukf.demo.exception.EmailAlreadyExistsException;
import sk.ukf.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Value("${job.titles}")
    private String[] jobTitles;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/view/{id}")
    public String viewEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employees/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("jobTitles", jobTitles);
        return "employees/form";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("jobTitles", jobTitles);
        return "employees/form";
    }

    @PostMapping
    public String createEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("jobTitles", jobTitles);
            return "employees/form";
        }

        try {
            employeeService.save(employee);
            return "redirect:/employees";
        } catch (EmailAlreadyExistsException ex) {
            bindingResult.rejectValue("email", "email.exist", ex.getMessage());
            model.addAttribute("jobTitles", jobTitles);
            return "employees/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
