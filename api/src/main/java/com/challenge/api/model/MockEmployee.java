package com.challenge.api.model;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.UUID;

public class MockEmployee implements Employee {
    private UUID uuid;

    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    private String fullName;

    @NotNull(message = "Salary is required") @Min(value = 0, message = "Salary must be positive")
    private Integer salary;

    @NotNull(message = "Age is required") @Min(value = 18, message = "Employee must be at least 18 years of age")
    @Max(value = 120, message = "Age must be realistic")
    private Integer age;

    @NotBlank(message = "Job title is required")
    @Size(min = 1, max = 150, message = "Job title must be between 1 and 150 characters")
    private String jobTitle;

    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    public MockEmployee() {}

    public MockEmployee(String firstName, String lastName, Integer salary, Integer age, String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@reliaquest.com";
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String name) {
        this.fullName = name;
    }

    @Override
    public Integer getSalary() {
        return salary;
    }

    @Override
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Instant getContractHireDate() {
        return contractHireDate;
    }

    @Override
    public void setContractHireDate(Instant date) {
        this.contractHireDate = date;
    }

    @Override
    public Instant getContractTerminationDate() {
        return contractTerminationDate;
    }

    @Override
    public void setContractTerminationDate(Instant date) {
        this.contractTerminationDate = date;
    }
}
