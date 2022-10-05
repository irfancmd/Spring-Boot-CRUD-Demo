package com.example.springBootCrudDemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootCrudDemo.entity.Employee;
import com.example.springBootCrudDemo.service.EmployeeService;

/*
 *  Since we're using Spring Data Rest in this version of the project, Spring boot will automatically provide us with a controller
 *  and service and so we don't want this controller to be used anymore. So we're removing the annotations to
 *  hide it from component scanning process
 *  
 *	Note: Spring Data JPA will search for JpaRepositories automatically and create REST controllers on its own. It will follow the
 *	HATEOAS format while doing so.
 *
 *	The default endpoint is localhost:{portnumber}/employees, but we've used a custom endpoint in the properties file
 *	
 *	It will follow simple plural convention for naming the endpoints
 * */

//@RestController
//@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	// Using constructor dependency injection
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("Employee with ID: " + employeeId + " does not exist.");
		}
		
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		
		employeeService.save(employee);
		
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);

		return employee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("Employee with ID: " + employeeId + " does not exist.");
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted employee with ID: " + employeeId + ".";
	}
}
