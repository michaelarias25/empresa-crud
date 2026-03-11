package com.parcial.empresacrud.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
