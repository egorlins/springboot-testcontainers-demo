package com.egorlins.springboot;

import com.egorlins.springboot.entity.Student;
import com.egorlins.springboot.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class SpringbootTestcontainersDemoApplicationTests {

	//NB it should be static, otherwise it will start and stop before and after every testcase not the whole test class
	@Container
	private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private MockMvc mockMvc;

	// given/when/then format - BDD style
	@Test
	public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {

		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		//given - setup or precondition
		List<Student> students = List.of(
				Student.builder().firstName("John").lastName("Smith").email("john@gmail.com").build(),
				Student.builder().firstName("Mark").lastName("More").email("mark@gmail.com").build());
		studentRepository.saveAll(students);

		//when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/students"));

		//then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(students.size())));

	}

}
