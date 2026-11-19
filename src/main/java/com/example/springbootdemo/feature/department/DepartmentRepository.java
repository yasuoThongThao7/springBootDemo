package com.example.springbootdemo.feature.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT new com.example.springbootdemo.feature.department.DepartmentStatDto(d.name, COUNT(e)) " +
            "FROM Department d LEFT JOIN Employee e ON d.id = e.department.id " +
            "GROUP BY d.id, d.name")
    List<DepartmentStatDto> findDepartmentStats();
}
