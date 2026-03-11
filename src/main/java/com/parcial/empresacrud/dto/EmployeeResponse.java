package com.parcial.empresacrud.dto;

import com.parcial.empresacrud.models.Employee;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponse {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Double salary;
    private Long departmentId;
    private String departmentName;

    public static EmployeeResponse fromEntity(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartment().getId())
                .departmentName(employee.getDepartment().getName())
                .build();
    }
}
