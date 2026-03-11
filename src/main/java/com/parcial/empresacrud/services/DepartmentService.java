package com.parcial.empresacrud.services;

import com.parcial.empresacrud.exceptions.ResourceNotFoundException;
import com.parcial.empresacrud.models.Department;
import com.parcial.empresacrud.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department update(Long id, Department departmentDetails) {
        Department department = findById(id);
        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        return departmentRepository.save(department);
    }

    public void delete(Long id) {
        Department department = findById(id);
        departmentRepository.delete(department);
    }
}
