package com.ems.controller.integrationtestcases;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ems.entity.Employee;
import com.ems.repository.impl.EmployeeRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "spring.h2.console.enabled=true")
@Transactional
public class EmsControllerIT {

	private static MockHttpServletRequest request;

	@Autowired
	private EmployeeRepositoryImpl employeeRepositoryImpl;

//	@Autowired
//	EmsServiceImpl emsServiceImpl;
//
//	@Autowired
//	private EmsController emsController;

//	@Autowired
//	SessionFactory sessionFactory;
//
//	@PersistenceContext
//	EntityManager entityManager;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	public static final MediaType APPLICATION_JSON = MediaType.APPLICATION_JSON;

	@Sql("/insertData.sql")
	@Test
	public void getAllEmployeesList_ShouldReturnListOfEmployees() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/ems/api/v1")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(2)));

	}

	@Test
	public void getAllEmployeesList_ShouldReturnException_WhenEmployeesNotPresentInDB() throws Exception {
		employeeRepositoryImpl.deleteAll();
		List<Employee> allEmployeesList = employeeRepositoryImpl.getAllEmployeesList();
		assertFalse(allEmployeesList.size() > 0);
		mockMvc.perform(MockMvcRequestBuilders.get("/ems/api/v1")).andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.status", is(404))).andExpect(jsonPath("$.message", is("Employee Not Found.")));
	}

	@Sql("/insertData.sql")
	@Test
	public void getEmployee_ShouldReturnPerticularEmployeeDetails_WhenEmployeeIdIsPassed() throws Exception {
		Optional<Employee> employeeById = employeeRepositoryImpl.getEmployeeById(2l);
		assertTrue(employeeById.isPresent());
		mockMvc.perform(MockMvcRequestBuilders.get("/ems/api/v1/{id}", 2)).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.fullName", is("Sushant Temkar")));
	}

	@Test
	public void getEmployee_ShouldReturnException_WhenEmployeeIdIsNotPresent() throws Exception {
		Optional<Employee> employeeById = employeeRepositoryImpl.getEmployeeById(0l);
		assertFalse(employeeById.isPresent());
		mockMvc.perform(MockMvcRequestBuilders.get("/ems/api/v1/{id}", 0)).andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.status", is(404))).andExpect(jsonPath("$.message", is("Employee Not Found.")));
	}

}
