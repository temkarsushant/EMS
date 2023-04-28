package com.ems.service;

import java.util.List;

import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.SaveEmployeeDetailsException;
import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;

public interface EmsService {

	public Employee save(EmployeeDto employeeDto) throws InvalidAgeException, SaveEmployeeDetailsException;

	public List<EmployeeDto> getAllEmployeesList() throws EmployeeNotFoundException;

	public EmployeeDto getEmployee(Long employeeId) throws EmployeeNotFoundException ;

}
