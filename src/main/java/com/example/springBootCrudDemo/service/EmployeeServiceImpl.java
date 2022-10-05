package com.example.springBootCrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springBootCrudDemo.dao.EmployeeDAO;
import com.example.springBootCrudDemo.dao.EmployeeRepository;
import com.example.springBootCrudDemo.entity.Employee;

/*
 *  Since we're using Spring Data Rest in this version of the project, Spring boot will automatically provide us with a controller
 *  and service and so we don't want this service to be used anymore. So we're removing the annotations to
 *  hide it from component scanning process
 * */

//@Service
public class EmployeeServiceImpl implements EmployeeService {
	
//	private EmployeeDAO employeeDAO;
	private EmployeeRepository employeeRepository;
	
	// Using constructor dependency injection
//	public EmployeeServiceImpl(@Qualifier("employeeDaoJpaImpl") EmployeeDAO employeeDAO) {
//		this.employeeDAO = employeeDAO;
//	}
	
	public EmployeeServiceImpl(EmployeeRepository emploRepository) {
		this.employeeRepository = emploRepository;
	}

	@Override
	// Spring Data JPA automatically handles transaction out of the box. So no need to put this annotation
	//@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	//@Transactional
	public Employee findById(int employeeId) {
		Optional<Employee> result = employeeRepository.findById(employeeId);
		
		Employee employee = null;
		
		if(result.isPresent()) {
			employee = result.get();
		}
		
		return employee;
	}

	@Override
	//@Transactional
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	//@Transactional
	public void deleteById(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
