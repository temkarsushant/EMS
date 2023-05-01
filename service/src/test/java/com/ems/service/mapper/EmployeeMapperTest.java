package com.ems.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.mapper.EmployeeMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	EmployeeMapper employeeMapper;

	@Test
	public void testEmployeeToDto_ShouldReturnEmployeeDto_WhenEmployeeEntityIsPassed() {
		// Expected Result
		EmployeeDto exEmployeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997",
				true);
		exEmployeeDto.setFullName("Sushant Temkar");

		// Actual Result
		Employee employee = new Employee("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true);
		EmployeeDto acEmployeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997",
				true);

		when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(acEmployeeDto);
//		Checking
		assertEquals(exEmployeeDto.getFullName(), employeeMapper.employeeToDto(employee).getFullName());
	}

	@Test
	public void testDtoToEmployee_ShouldReturnEmployeeEntity_WhenEmployeeDtoIsPassed() {
		// Expected Result
		Employee exEmployee = new Employee("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true);

		// Actual Result
		Employee acEmployee = new Employee("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true);
		EmployeeDto employeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997",
				true);

		when(modelMapper.map(employeeDto, Employee.class)).thenReturn(acEmployee);
//		Checking
		assertEquals(exEmployee.getFirstName(), employeeMapper.dtoToEmployee(employeeDto).getFirstName());
	}

}
