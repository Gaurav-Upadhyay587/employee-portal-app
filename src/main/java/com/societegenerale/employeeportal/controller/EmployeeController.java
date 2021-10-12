package com.societegenerale.employeeportal.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.societegenerale.employeeportal.entity.Employee;
import com.societegenerale.employeeportal.exception.EmployeeErrorResponse;
import com.societegenerale.employeeportal.exception.EmployeeNotFoundException;
import com.societegenerale.employeeportal.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<EmployeeErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<EmployeeErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/v1.1/filters/{sort}")
	public ResponseEntity<List<Employee>> fetchAllEmployeesSortedByFirstName(@PathVariable String sort) {
		List<Employee> employees;
		if("firstName_asc".equals(sort)) {
			employees = employeeService.fetchEmployeesInAscendingOrderOfName();
		}else {
			employees = employeeService.fetchAllEmployees();
		}
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/v1.0")
	public ResponseEntity<List<Employee>> fetchAllEmployees() {
		List<Employee> employees = employeeService.fetchAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/v1.0/{employeeId}")
	public ResponseEntity<Employee> fetchEmployeeDetail(@PathVariable long employeeId) {
		Optional<Employee> employee = employeeService.fetchEmployeeDetail(employeeId);
		if (employee.isPresent())
			return ResponseEntity.ok(employee.get());
		else
			throw new EmployeeNotFoundException("No Employee is found with given id: "+employeeId);
		
	}
	
	@PostMapping("/v1.0")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		employee = employeeService.createEmployee(employee);
		if(employee != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/employees/"+employee.getEmployeeId()).buildAndExpand(employee.getEmployeeId()).toUri();
			return ResponseEntity.created(location).body(employee);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
}
