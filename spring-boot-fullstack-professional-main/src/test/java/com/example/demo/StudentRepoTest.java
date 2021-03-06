package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.student.Gender;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentRepoTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void ItShouldCheckSelectExistEmailTest() {
		String email = "kumar@gmail.com";
		// given
		Student student = new Student();
		student.setEmail(email);
		student.setGender(Gender.MALE);
		student.setName("raja juluru");

		studentRepository.save(student);
		// when
		Boolean expected = studentRepository.selectExistsEmail(email);
		// then
		assertThat(expected).isTrue();
	}
}
