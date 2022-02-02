package com.juluru;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juluru.entities.Employee;
import com.juluru.repositories.EmployeeRepositories;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RestServerProjectApplicationTests {
	
	@Autowired
	public EmployeeRepositories employeeRepositories;

	@Test
	public void sampleTest() {
		List<Employee> findAll = employeeRepositories.findAll();
		assertThat(findAll.size()).isGreaterThan(0);
	}

}
