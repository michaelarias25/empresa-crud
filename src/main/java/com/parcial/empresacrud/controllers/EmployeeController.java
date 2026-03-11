package com.parcial.empresacrud.controllers;

import com.parcial.empresacrud.dto.EmployeeRequest;
import com.parcial.empresacrud.dto.EmployeeResponse;
import com.parcial.empresacrud.models.Employee;
import com.parcial.empresacrud.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        List<EmployeeResponse> employees = employeeService.findAll()
                .stream()
                .map(EmployeeResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(EmployeeResponse.fromEntity(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
        Employee employee = Employee.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .salary(request.getSalary())
                .build();

        Employee saved = employeeService.save(employee, request.getDepartmentId());
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeResponse.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        Employee employee = Employee.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .salary(request.getSalary())
                .build();

        Employee updated = employeeService.update(id, employee, request.getDepartmentId());
        return ResponseEntity.ok(EmployeeResponse.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
