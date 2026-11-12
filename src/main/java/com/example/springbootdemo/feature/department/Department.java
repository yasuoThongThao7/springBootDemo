package com.example.springbootdemo.feature.department;

import com.example.springbootdemo.feature.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @OneToMany(
        mappedBy = "department",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    private List<Employee> employees;
}
