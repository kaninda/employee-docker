package com.aka.service.impl;

import com.aka.controller.exception.EmployeeFoundException;
import com.aka.model.Employee;
import com.aka.repository.EmployeeRepository;
import com.aka.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll () {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional <Employee> findOne(Long id) {
        return employeeRepository.findById(id);
    }

}
