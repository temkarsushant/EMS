package com.ems.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ems.entity.Employee;
import com.ems.entity.QEmployee;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@TestPropertySource("/application-test.properties")
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeRepositoryImplTest {

	@Autowired
	private EmployeeRepositoryImpl employeeRepositoryImpl;

	@Mock
	SessionFactory sessionFactory;

	@PersistenceContext
	EntityManager entityManager;

	@BeforeEach
	public void setUp() {
		employeeRepositoryImpl.deleteAll();

		Employee employee = new Employee("Sushant", "Temkar", "7972501198", "E1", "20000", "05/08/1997", true);
		employeeRepositoryImpl.save(employee);
	}

	@Test
	@Order(1)
	public void getPerticularEmployee_ShouldReturnPerticularEmployeeDetails() {
		// Expected
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

		Optional<Employee> expectedEmployee = Optional.ofNullable(jpaQueryFactory.select(QEmployee.employee)
				.from(QEmployee.employee).where(QEmployee.employee.UUID.eq(1l)).fetchFirst());
		// Actual
		Optional<Employee> actualEmployee = employeeRepositoryImpl.getEmployeeById(1l);
		// Test
		assertEquals(expectedEmployee.get().getFirstName(), actualEmployee.get().getFirstName());
	}

	@Test
	@Order(2)
	public void getAllEmployeesList_ShouldReturnListOfEmployees() {
		// Expected
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		List<Employee> expectedEmployeeList = jpaQueryFactory.selectFrom(QEmployee.employee).fetch();

		// Actual
		List<Employee> actualEmployeeList = employeeRepositoryImpl.getAllEmployeesList();

		// Test
		assertTrue(actualEmployeeList.size() > 0);
		assertThat(actualEmployeeList.size()).isEqualTo(expectedEmployeeList.size());
		assertEquals("Sushant", actualEmployeeList.get(0).getFirstName());
	}

	@Test
	@Order(3)
	public void saveEmployee_ShouldReturnEmployeeId() {

		Employee employee = new Employee("Sushant", "Temkar", "7972501198", "E1", "20000", "05/08/1997", true);

		assertEquals(employee.getFirstName(), employeeRepositoryImpl.save(employee).getFirstName());
	}

}
