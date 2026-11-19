package com.example.springbootdemo.feature.employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);

    Employee findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentId(String department_id);

}
