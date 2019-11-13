package com.societegenerale.employeeportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.societegenerale.employeeportal.entity.Employee;
import com.societegenerale.employeeportal.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepository employeeRepository;	
	
	public List<Employee> fetchEmployeesInAscendingOrderOfName(){	
		LOGGER.info("      Sort.by(firstName)      ");
		Iterable<Employee> employees = employeeRepository.findAll(Sort.by("firstName"));
		List<Employee> employeesList = new ArrayList<Employee>();
		employees.forEach(employeesList::add);		
		return employeesList;
	}
	
	public List<Employee> fetchAllEmployees(){		
		Iterable<Employee> employees = employeeRepository.findAll();
		List<Employee> employeesList = new ArrayList<Employee>();
		employees.forEach(employeesList::add);		
		return employeesList;
	}

	public Optional<Employee> fetchEmployeeDetail(long employeeId) {
		LOGGER.info("fetching employee of id: "+employeeId);
		Optional<Employee>  employee = employeeRepository.findById(employeeId);		
		return employee;
		}

	public Employee createEmployee(Employee employee) {		
		return employeeRepository.save(employee);
	}

}
