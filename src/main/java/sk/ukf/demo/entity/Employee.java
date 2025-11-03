package sk.ukf.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Employee () {
    }

    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Birth date is required.")
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Email is required.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Please enter a valid email address (e.g., name@domain.com)."
    )
    @Size(max = 100, message = "Email cannot exceed 100 characters.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(
            regexp = "^\\+?\\d{1,3}?[- .]?\\(?\\d{1,4}\\)?[- .]?\\d{1,4}[- .]?\\d{1,9}$",
            message = "Please enter a valid phone number (e.g., +421 903 123 456 or 0903-123-456)."
    )
    @Column(name = "phone")

    private String phone;

    @NotBlank(message = "Job title is required.")
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull(message = "Salary is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be positive.")
    @Column(name = "salary")
    private BigDecimal salary;

    @NotBlank(message = "Job type is required.")
    @Column(name = "full_time")
    private String fullTime;

    public Employee(String firstName, String lastName, LocalDate birthDay, String email, String phone, String jobTitle, BigDecimal salary, String fullTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDay;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.fullTime = fullTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", fullTime=" + fullTime +
                '}';
    }
}