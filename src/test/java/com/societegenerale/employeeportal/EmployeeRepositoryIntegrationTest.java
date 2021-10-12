package com.societegenerale.employeeportal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.societegenerale.employeeportal.entity.Employee;
import com.societegenerale.employeeportal.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Test
    public void whenFindByID_thenReturnEmployee() {
        // given
        Employee gaurav = new Employee("Gaurav", "Upadhyay","MALE", new Date(System.currentTimeMillis()),"IT");
        gaurav = entityManager.persist(gaurav);
        entityManager.flush();
     
        // when
        Optional<Employee> found = employeeRepository.findById(gaurav.getEmployeeId());
     
        // then
        assertEquals(gaurav.getFirstName(), found.get().getFirstName());
        assertEquals(gaurav.getLastName(), found.get().getLastName());
    }
}
