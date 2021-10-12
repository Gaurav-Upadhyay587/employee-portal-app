package com.societegenerale.employeeportal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.societegenerale.employeeportal.entity.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
