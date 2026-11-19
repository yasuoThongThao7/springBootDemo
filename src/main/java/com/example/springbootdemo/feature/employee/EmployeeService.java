package com.example.springbootdemo.feature.employee;

import com.example.springbootdemo.feature.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId().toString(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getDepartment().getId())
                )
                .toList();
    }


    public void addEmployee(EmployeeDTO employee) {

        var department = departmentRepository.findById(employee.departmentId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid department ID: " + employee.departmentId())
                    );

        var newEmployee = new Employee();

        newEmployee.setName(employee.name());
        newEmployee.setEmail(employee.email());
        newEmployee.setDepartment(department);

        log.info("Adding employee: {}", newEmployee.getName());
        if (newEmployee.getName() != null && !newEmployee.getName().isEmpty()) {
            employeeRepository.save(newEmployee);
            log.info("Employee added successfully: {}", newEmployee.getName());
        } else {
            log.warn("Failed to add employee: {}", newEmployee.getName());
        }
    }

    public EmployeeDTO getEmployeeById(Long id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));

        return new EmployeeDTO(
                employee.getId().toString(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment().getId()
        );
    }

    @Cacheable("employeeCount")
    public long getEmployeeCount() {
        return employeeRepository.count();
    }

}
