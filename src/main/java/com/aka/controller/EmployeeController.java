package com.aka.controller;

import com.aka.controller.exception.EmployeeFoundException;
import com.aka.model.Employee;
import com.aka.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping
    public Employee create (@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public Employee findOne (@PathVariable Long id){
        return employeeService.findOne(id).orElseThrow(EmployeeFoundException::new);
    }

    @GetMapping("/name/{name}")
    public Employee findByName(@PathVariable String name){
        return employeeService.findByName(name).orElseThrow(() -> new EmployeeFoundException("Cannot find Employee with this name"));
    }
}