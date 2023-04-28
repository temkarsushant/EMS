package com.ems.mapper;

import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.utility.CommonUtility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

	@Autowired
	ModelMapper modelMapper;

	public EmployeeDto employeeToDto(Employee employee) {
		EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);
		employeeDto.setAge(CommonUtility.calculateAge(CommonUtility.getLocalDate(employee.getDateOfBirth())));
		employeeDto.setDesignation(CommonUtility.getEmployeeDesignation(employee.getGrade()));
		employeeDto.setFullName(employee.getFirstName() + " " + employee.getLastName());
		return employeeDto;
	}

	public Employee dtoToEmployee(EmployeeDto employeeDto) {
		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		return employee;
	}
}
