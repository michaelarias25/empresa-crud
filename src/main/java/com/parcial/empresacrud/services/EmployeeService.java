package com.parcial.empresacrud.services;

import com.parcial.empresacrud.exceptions.ResourceNotFoundException;
import com.parcial.empresacrud.models.Department;
import com.parcial.empresacrud.models.Employee;
import com.parcial.empresacrud.repositories.DepartmentRepository;
import com.parcial.empresacrud.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee save(Employee employee, Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employeeDetails, Long departmentId) {
        Employee employee = findById(id);

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        employee.setName(employeeDetails.getName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setSalary(employeeDetails.getSalary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
    }
}
