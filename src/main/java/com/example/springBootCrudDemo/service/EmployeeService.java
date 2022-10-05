package com.example.springBootCrudDemo.service;

import java.util.List;

import com.example.springBootCrudDemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int employeeId);

	public void save(Employee employee);

	public void deleteById(int employeeId);
}
