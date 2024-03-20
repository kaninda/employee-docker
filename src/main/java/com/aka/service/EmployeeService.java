package com.aka.service;

import com.aka.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        return List.of(
                new Employee("Arnaud", 1000)
        );
    }

}