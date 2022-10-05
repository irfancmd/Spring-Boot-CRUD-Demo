package com.example.springBootCrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springBootCrudDemo.dao.EmployeeDAO;
import com.example.springBootCrudDemo.dao.EmployeeRepository;
import com.example.springBootCrudDemo.entity.Employee;

@Service
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
