package com.ems.controller.unittestcases;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.ems.controller.EmsController;
import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.InvalidGradeException;
import com.ems.customexception.SaveEmployeeDetailsException;
import com.ems.dto.EmployeeDto;
import com.ems.service.impl.EmsServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class EmsControllerTest {
	@Mock
	private EmsServiceImpl emsService;

	@InjectMocks
	private EmsController emsController;

	@Test
	public void testSaveEmployee_ShouldReturnEmloyeeDetails_WhenEmployeeDetailsIsPassed()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException {

		EmployeeDto actualEmployeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997",
				true);

		Mockito.when(emsService.save(actualEmployeeDto)).thenReturn(actualEmployeeDto);

		ResponseEntity<EmployeeDto> responseEntity = emsController.saveEmployee(actualEmployeeDto);

		assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);

	}

	@Test
	public void testsaveEmployee_ShouldReturnInvalidGradeException_WhenInvalidGradeIsPassed()
			throws InvalidAgeException, SaveEmployeeDetailsException {
		EmployeeDto employeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "L1", "salary", "05/08/1997",
				true);

		// Test
		InvalidGradeException thrown = Assertions.assertThrows(InvalidGradeException.class, () -> {
			emsController.saveEmployee(employeeDto);
		});

	}

	@Test
	public void testGetAllEmployeesList_ShouldReturnListOfEmployees()
			throws InvalidAgeException, SaveEmployeeDetailsException, EmployeeNotFoundException {
		// Expected Result
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		
		EmployeeDto employeeDto1 = new EmployeeDto("Sushant Temkar", "7972501198", "E1", "Software Engg", "20000",
				"05/08/1997", 25, true);
		EmployeeDto employeeDto2 = new EmployeeDto("Sushant Temkar", "7972501198", "E1", "Software Engg", "20000",
				"05/08/1997", 25, true);
		employeeDtos.add(employeeDto1);
		employeeDtos.add(employeeDto2);

		// Mocking
		Mockito.when(emsService.getAllEmployeesList()).thenReturn(employeeDtos);

		ResponseEntity<List<EmployeeDto>> listOfEntity = emsController.getAllEmployeesList();
		assertThat(listOfEntity.getBody().get(0).getFullName()).isEqualTo("Sushant Temkar");
	}

	@Test
	public void testGetEmployee_ShouldReturnPerticularEmployee()
			throws InvalidAgeException, SaveEmployeeDetailsException, EmployeeNotFoundException {
		// Expected Result
		EmployeeDto employeeDto = new EmployeeDto("Sushant Temkar", "7972501198", "E1", "Software Engg", "20000",
				"05/08/1997", 25, true);

		// Mocking
		Mockito.when(emsService.getEmployee(1l)).thenReturn(employeeDto);

		ResponseEntity<EmployeeDto> employeeDetails = emsController.getEmployee(1l);
		
		assertThat(employeeDetails.getBody().getFullName()).isEqualTo("Sushant Temkar");

	}

}
