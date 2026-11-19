package com.example.springbootdemo.feature.employee;

import com.example.springbootdemo.core.util.UtilityService;
import com.example.springbootdemo.feature.department.DepartmentService;
import com.example.springbootdemo.feature.department.DepartmentStatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping ("/list")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO(null, null, null, null));
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute EmployeeDTO employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/count")
    @ResponseBody
    public long getEmployeeCount() {
        return employeeService.getEmployeeCount();
    }

    @GetMapping("/api/employees/statistics")
    @ResponseBody
    public List<DepartmentStatDto> getEmployeeStatistics() {
        return departmentService.getDepartmentStats();
    }

    @GetMapping("/employees/statistics")
    public String showEmployeeStatistics(Model model) {
        List<DepartmentStatDto> statistics = departmentService.getDepartmentStats();
        model.addAttribute("statistics", statistics);
        return "employees/statistics";
    }
//    private final UtilityService utilityService;
//
//    private List<EmployeeDTO> employees = List.of(
//            new EmployeeDTO("1", "John Doe", "john.doe@example.com"),
//            new EmployeeDTO("2", "Jane Smith", "jane.smith@example.com")
//    );
//
//    @GetMapping("/hello")
//    public String getEmployee() {
//        return "Hello from EmployeeController!";
//    }
//
//    @GetMapping("/hello/{name}")
//    public String Hello(@PathVariable("name") String name) {
//        return utilityService.getGreetingMessage(name);
//    }
//
//    @GetMapping("/employees")
//    public List<EmployeeDTO> getAllEmployees() {
//        return employees;
//    }
//
//    @PostMapping("/employees")
//    public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        employees.add(employeeDTO);
//    }


}
