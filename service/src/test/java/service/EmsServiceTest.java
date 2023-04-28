package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.InvalidGradeException;
import com.ems.customexception.SaveEmployeeDetailsException;
import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.mapper.EmployeeMapper;
import com.ems.repository.EmployeeRepository;
import com.ems.service.impl.EmsServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class EmsServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmsServiceImpl emsService;

	@Mock
	EmployeeMapper employeeMapper;

	@Mock
	ModelMapper modelMapper;

	@Test
	public void testSaveEmployee_ShouldReturnEmloyeeDetailsWithId_WhenEmployeeDetailsIsPassed()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException {
		// Expected Result
		Optional<Employee> exEmployee = Optional
				.of(new Employee(1l, "Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true));

		// Actual Result
		Employee employee = new Employee("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true);
		EmployeeDto employeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997",
				true);
		// exEmployee.setUUID(1l);

		Optional<Employee> acEmployee = Optional
				.of(new Employee(1l, "Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true));

		Long actualResult = 1l;

		// Mocking
		Mockito.when(employeeMapper.dtoToEmployee(employeeDto)).thenReturn(employee);
		Mockito.when(employeeRepository.saveEmployee(employee)).thenReturn(actualResult);
		Mockito.when(employeeRepository.getEmployeeById(1l)).thenReturn(acEmployee);

//		Checking
		assertEquals(exEmployee.get().getUUID(), emsService.save(employeeDto).getUUID());
	}

	@Test
	public void testSaveEmployee_ShouldReturnInvalidAgeException_WhenAgeIsBelow21()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException {
		// Expected Result
		String expectedMessage = "Employee is under aged and cannot be onboarded till his age is 21.";

		// Actual Result
		EmployeeDto employee = new EmployeeDto();
		employee.setDateOfBirth("05/08/2019");

		// Test
		InvalidAgeException thrown = Assertions.assertThrows(InvalidAgeException.class, () -> {
			emsService.save(employee);
		});

//		Checking
		assertEquals(expectedMessage, thrown.getMessage());
	}

	@Test
	public void testSaveEmployee_ShouldReturnSaveEmployeeDetailsException_WhenExcetpionOccursWhileSavingEmployeeData()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException {
		// Expected Result
		String expectedMessage = "Could not able to save Employee details.";
		// Actual Result
		Employee employee = new Employee("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997", true);
		EmployeeDto employeeDto = new EmployeeDto("Sushant", "Temkar", "7972501198", "E1", "salary", "05/08/1997",
				true);
		Long actualResult = null;
		// Mocking
		Mockito.when(employeeMapper.dtoToEmployee(employeeDto)).thenReturn(employee);
		Mockito.when(employeeRepository.saveEmployee(employee)).thenReturn(actualResult);
		// Checking
		SaveEmployeeDetailsException thrown = Assertions.assertThrows(SaveEmployeeDetailsException.class, () -> {
			emsService.save(employeeDto);
		});
		// Checking
		assertEquals(expectedMessage, thrown.getMessage());
	}

	@Test
	public void testGetAllEmployeesList_ShouldReturnListOfEmployeeDtos()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException, EmployeeNotFoundException {
		// Expected Result
		List<EmployeeDto> expectedResultList = new ArrayList<>();
		EmployeeDto employeeDto1 = new EmployeeDto("Sushant Temkar", "7972501198", "E1", "Software Engg", "20000",
				"05/08/1997", 25, true);
		EmployeeDto employeeDto2 = new EmployeeDto("Sushant Temkar", "7972501198", "E1", "Software Engg", "20000",
				"05/08/1997", 25, true);
		expectedResultList.add(employeeDto1);
		expectedResultList.add(employeeDto2);

		// Actual Result
		List<Employee> actualResultList = new ArrayList<>();
		Employee employee1 = new Employee("Sushant", "Temkar", "7972501198", "E1", "20000", "05/08/1997", true);
		Employee employee2 = new Employee("Sushant", "Temkar", "7972501198", "E1", "20000", "05/08/1997", true);
		actualResultList.add(employee1);
		actualResultList.add(employee2);

		// Mocking
		Mockito.when(employeeRepository.getAllEmployeesList()).thenReturn(actualResultList);
		// Checking
		// assertEquals(expectedResultList, emsService.getAllEmployeesList());
		Assertions.assertTrue(expectedResultList.size() == emsService.getAllEmployeesList().size());
	}

	@Test
	public void testGetAllEmployeesList_ShouldReturnEmployeeNotFoundException_WhenEmployeesAreNotPresentInDB()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException, EmployeeNotFoundException {
		// Expected Result
		String expectedMessage = "Employee Not Found.";

		Mockito.when(employeeRepository.getAllEmployeesList()).thenReturn(null);

		EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
			emsService.getAllEmployeesList();
		});

		assertEquals(expectedMessage, thrown.getMessage());
	}

	@Test
	public void testGetEmployee_ShouldReturnEmployeeDto_WhenEmployeeIdIsPassed()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException, EmployeeNotFoundException {
		// Expected Result
		EmployeeDto employeeDto = new EmployeeDto("Sushant Temkar", "7972501198", "E1", "Software Engg", "20000",
				"05/08/1997", 25, true);

		// Actual Result
		Optional<Employee> employee = Optional
				.of(new Employee("Sushant", "Temkar", "7972501198", "E1", "20000", "05/08/1997", true));

		// Mocking
		Mockito.when(employeeRepository.getEmployeeById(1l)).thenReturn(employee);
		Mockito.when(employeeMapper.employeeToDto(employee.get())).thenReturn(employeeDto);
		// Checking
		// assertEquals(expectedResultList, emsService.getAllEmployeesList());
		Assertions.assertTrue(employeeDto.getDesignation().equals(emsService.getEmployee(1l).getDesignation()));
	}

	@Test
	public void testGetEmployee_ShouldReturnEmployeeNotFoundException_WhenEmployeesAreNotPresentInDB()
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException, EmployeeNotFoundException {
		// Expected Result
		String expectedMessage = "Employee Not Found.";

		Mockito.when(employeeRepository.getEmployeeById(1l)).thenReturn(null);

		EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
			emsService.getEmployee(1l);
		});

		assertEquals(expectedMessage, thrown.getMessage());
	}

}
