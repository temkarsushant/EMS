package com.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.ems.constants.CommonMessagesConstants;
import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.SaveEmployeeDetailsException;
import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.mapper.EmployeeMapper;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmsService;
import com.ems.utility.CommonUtility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmsServiceImpl implements EmsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public Employee save(EmployeeDto employeeDto) throws InvalidAgeException, SaveEmployeeDetailsException {

		if (CommonUtility.calculateAge(CommonUtility.getLocalDate(employeeDto.getDateOfBirth())) < 21) {
			throw new InvalidAgeException(CommonMessagesConstants.InvalidAgeExceptionConstant);
		}

		Long id = employeeRepository.saveEmployee(employeeMapper.dtoToEmployee(employeeDto));
		if (id != null) {
			Optional<Employee> employeeDetails = employeeRepository.getEmployeeById(id);
			return employeeDetails.get();
		} else {
			throw new SaveEmployeeDetailsException(CommonMessagesConstants.SaveEmployeeDetailsExceptionConstant);
		}
	}

	@Override
	public List<EmployeeDto> getAllEmployeesList() throws EmployeeNotFoundException {
		List<Employee> employeList = employeeRepository.getAllEmployeesList();
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		System.out.println(employeList);

		if (employeList != null && employeList.size() > 0) {

			employeList.stream().forEach((employee) -> {
				employeeDtos.add(employeeMapper.employeeToDto(employee));
			});

		} else {
			throw new EmployeeNotFoundException(CommonMessagesConstants.EmployeeNotFoundException);
		}

		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployee(Long eid) throws EmployeeNotFoundException {
		Optional<Employee> employeeDetails = employeeRepository.getEmployeeById(eid);

		if (employeeDetails != null && employeeDetails.isPresent()) {
			Employee employee = employeeDetails.get();

			EmployeeDto employeeDto = employeeMapper.employeeToDto(employee);
			return employeeDto;
		} else {
			throw new EmployeeNotFoundException(CommonMessagesConstants.EmployeeNotFoundException);
		}
	}

}
