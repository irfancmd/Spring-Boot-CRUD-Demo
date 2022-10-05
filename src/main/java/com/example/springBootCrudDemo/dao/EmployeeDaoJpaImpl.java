package com.example.springBootCrudDemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootCrudDemo.entity.Employee;

/*
 * This is the standard JPA implementation of the EmployeeDAO. This will enable us to use
 * switch between any JPA implementation like Hibernate, EclipseLink etc. with ease. By default,
 * Spring Boot uses Hibernate
 * */
@Repository
public class EmployeeDaoJpaImpl implements EmployeeDAO {
	
	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		// This is the Query class from javax.persistence package
		// Standard JPA uses a query language called JPQL similar to Hibernate's HQL
		Query query = entityManager.createQuery("from Employee");
		
		List<Employee> employees = query.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		// Save or update
		Employee savedEmployee = entityManager.merge(employee);
		
		// We have to do this part manually if we use standard JPA
		employee.setId(savedEmployee.getId());
	}

	@Override
	public void deleteById(int employeeId) {
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", employeeId);
		
		query.executeUpdate();
	}

}
