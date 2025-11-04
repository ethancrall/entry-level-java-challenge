package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.MockEmployee;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Mock in-memory database
    private final List<Employee> employees = new ArrayList<>();

    // Populate mock data when service is constructed
    @PostConstruct
    public void initializeMockData() {
        addMockEmployee("Jitin", "Patel", 200000, 35, "Senior Development Manager");
        addMockEmployee("Jose", "Tristani", 12000, 30, "Senior Software Engineer");
        addMockEmployee("John", "Gangemi", 120000, 64, "Senior Software Engineer");
        addMockEmployee("Junior", "Recinos", 120000, 30, "Senior Software Engineer");
        addMockEmployee("Miles", "Laning", 85000, 22, "Junior Software Engineer");
    }

    // Helper method for initializing mock data
    private void addMockEmployee(String firstName, String lastName, Integer salary, Integer age, String jobTitle) {
        MockEmployee mockEmployee = new MockEmployee(firstName, lastName, salary, age, jobTitle);
        mockEmployee.setUuid(UUID.randomUUID());
        mockEmployee.setContractHireDate(Instant.now());
        employees.add(mockEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Return a copy of employees list to avoid external manipulation
        return new ArrayList<>(employees);
    }

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        // Return first match
        return employees.stream()
                .filter(employee -> employee.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (isDuplicateEntry(employee)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Employee " + employee.getFullName() + " already exists");
        }

        MockEmployee mockEmployee = new MockEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getAge(),
                employee.getJobTitle());
        mockEmployee.setUuid(UUID.randomUUID());
        mockEmployee.setContractHireDate(Instant.now());

        employees.add(mockEmployee);
        return mockEmployee;
    }

    // Detect duplicate entries in the "database"
    private boolean isDuplicateEntry(Employee employee) {
        return employees.stream()
                .anyMatch(e -> e.getFirstName().equalsIgnoreCase(employee.getFirstName())
                        && e.getLastName().equalsIgnoreCase(employee.getLastName())
                        && e.getSalary().equals(employee.getSalary())
                        && e.getAge().equals(employee.getAge())
                        && e.getJobTitle().equalsIgnoreCase(employee.getJobTitle()));
    }
}
