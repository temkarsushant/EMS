package com.ems.service;

import java.util.List;

import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.InvalidGradeException;
import com.ems.customexception.SaveEmployeeDetailsException;
import com.ems.dto.EmployeeDto;

public interface EmsService {

	public EmployeeDto save(EmployeeDto employeeDto) throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException;

	public List<EmployeeDto> getAllEmployeesList() throws EmployeeNotFoundException;

	public EmployeeDto getEmployee(Long employeeId) throws EmployeeNotFoundException ;

}
