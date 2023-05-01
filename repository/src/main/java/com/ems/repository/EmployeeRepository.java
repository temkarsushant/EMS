package com.ems.repository;

import java.util.List;
import java.util.Optional;

import com.ems.entity.Employee;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {

	public List<Employee> getAllEmployeesList();

	public Optional<Employee> getEmployeeById(Long eid);

//	public Long saveEmployee(Employee employee);

}
