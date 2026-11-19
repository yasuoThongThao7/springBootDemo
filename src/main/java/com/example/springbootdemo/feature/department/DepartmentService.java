package com.example.springbootdemo.feature.department;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final  DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> new DepartmentDTO(
                        department.getId(),
                        department.getName())
                )
                .toList();
    }

    public List<DepartmentStatDto> getDepartmentStats() {
        return departmentRepository.findDepartmentStats();
    }
}
