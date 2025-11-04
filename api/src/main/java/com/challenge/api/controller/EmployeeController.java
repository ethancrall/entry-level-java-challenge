package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import com.challenge.api.model.MockEmployee;
import com.challenge.api.service.EmployeeService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Returns a list of all the employees
     * @return One or more employees.
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Returns an employee that matches the given UUID, if one exists
     * @param uuid Employee UUID
     * @return Requested employee (if exists)
     * @throws ResponseStatusException if getEmployeeByUuid returns a null object
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        Employee employee = employeeService.getEmployeeByUuid(uuid);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with UUID '" + uuid + "' not found");
        }
        return employee;
    }

    /**
     * Creates a new employee and adds it to the "database"
     * @param requestBody Request body containing information for the employee to be created
     * @return Newly created Employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@Valid @RequestBody MockEmployee requestBody) {
        return employeeService.createEmployee(requestBody);
    }
}
