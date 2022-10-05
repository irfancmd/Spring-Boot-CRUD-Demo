package com.example.springBootCrudDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.springBootCrudDemo.entity.Employee;

/*
 * This is the Spring Data JPA implementation of the Employee DAO.
 * It will automatically provide us with the all the necessary CRUD methods by its own
 * */

// We can customize default plural name used by Spring Data REST usign this annotatin
// @RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// No implementation needed for basic CRUD methods
}
