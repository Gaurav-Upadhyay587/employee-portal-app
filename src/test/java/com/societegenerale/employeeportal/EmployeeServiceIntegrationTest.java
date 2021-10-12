package com.societegenerale.employeeportal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.societegenerale.employeeportal.entity.Employee;
import com.societegenerale.employeeportal.repository.EmployeeRepository;
import com.societegenerale.employeeportal.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceIntegrationTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeService();
		}
	}

	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
    private EmployeeRepository employeeRepository;
	
	@Before
	public void setUp() {
		Employee gaurav = new Employee("Gaurav", "Upadhyay", "male", new Date(System.currentTimeMillis()), "IT");
		gaurav.setEmployeeId(10111L);
		Mockito.when(employeeRepository.findById(10111L)).thenReturn(Optional.of(gaurav));
	}

	@Test
	public void whenValidId_thenEmployeeShouldBeFound() {
		Employee found = employeeService.fetchEmployeeDetail(10111L).get();
		assertEquals("Gaurav", found.getFirstName());
		assertEquals("Upadhyay", found.getLastName());
	}
}