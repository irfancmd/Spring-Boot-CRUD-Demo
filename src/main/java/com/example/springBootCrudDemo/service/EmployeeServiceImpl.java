package com.example.springBootCrudDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springBootCrudDemo.dao.EmployeeDAO;
import com.example.springBootCrudDemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;
	
	// Using constructor dependency injection
	public EmployeeServiceImpl(@Qualifier("employeeDaoJpaImpl") EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int employeeId) {
		return employeeDAO.findById(employeeId);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int employeeId) {
		employeeDAO.deleteById(employeeId);
	}

}
