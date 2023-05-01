package com.ems.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee, Long> implements EmployeeRepository {

	@Autowired
	SessionFactory sessionFactory;

	public EmployeeRepositoryImpl(EntityManager em) {
		super(Employee.class, em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> getAllEmployeesList() {
		List<Employee> fetchAll = queryFactory.select(emloyee).from(emloyee).fetch();
		return fetchAll;
	}

	@Override
	public Optional<Employee> getEmployeeById(Long eid) {
		return Optional.ofNullable(queryFactory.select(emloyee).from(emloyee).where(emloyee.UUID.eq(eid)).fetchFirst());
	}

}
