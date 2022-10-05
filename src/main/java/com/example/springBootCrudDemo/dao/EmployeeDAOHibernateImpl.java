package com.example.springBootCrudDemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springBootCrudDemo.entity.Employee;

/*
 *	This is the Hibernate specific EmployeeDAO implementation. So it will only work with Hibernate. 
 * */
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	private EntityManager entityManager;

	// The entityManager will be injected automatically via constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		// Get Hibernate Session
		Session session = entityManager.unwrap(Session.class);
		
		// Creating the query
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		
		// Fetch employee list
		List<Employee> employees = query.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, employeeId);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int employeeId) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", employeeId);
		
		query.executeUpdate();
	}

}
