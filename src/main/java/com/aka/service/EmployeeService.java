package com.aka.service;
import com.aka.model.Employee;
import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    List<Employee> findAll ();

    Optional <Employee> findByName (String name);

    Employee create (Employee employee);

    Optional<Employee> findOne(Long id);

}