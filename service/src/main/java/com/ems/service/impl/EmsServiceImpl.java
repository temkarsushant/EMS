package com.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ems.constants.CommonMessagesConstants;
import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.InvalidGradeException;
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
	public EmployeeDto save(EmployeeDto employeeDto)
			throws InvalidAgeException, SaveEmployeeDetailsException, InvalidGradeException {

		if (CommonUtility.calculateAge(CommonUtility.getLocalDate(employeeDto.getDateOfBirth())) < 21) {
			throw new InvalidAgeException(CommonMessagesConstants.InvalidAgeExceptionConstant);
		}

		Employee employeeDetails = employeeRepository.save(employeeMapper.dtoToEmployee(employeeDto));

		if (employeeDetails != null) {
			EmployeeDto employeeDtoDetails = employeeMapper.employeeToDto(employeeDetails);
			return employeeDtoDetails;
		} else {
			throw new SaveEmployeeDetailsException(CommonMessagesConstants.SaveEmployeeDetailsExceptionConstant);
		}
	}

	@Override
	public List<EmployeeDto> getAllEmployeesList() throws EmployeeNotFoundException {
		List<Employee> employeList = employeeRepository.getAllEmployeesList();
		
		List<EmployeeDto> employeeDtos = new ArrayList<>();

		if (employeList != null && employeList.size() > 0) {
			employeList.stream().forEach((employee) -> {employeeDtos.add(employeeMapper.employeeToDto(employee));});
		} else {
			throw new EmployeeNotFoundException(CommonMessagesConstants.EmployeeNotFoundException);
		}

		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployee(Long eid) throws EmployeeNotFoundException {
		Optional<Employee> employeeDetails = employeeRepository.getEmployeeById(eid);

		if (employeeDetails != null && employeeDetails.isPresent()) {
			EmployeeDto employeeDto = employeeMapper.employeeToDto(employeeDetails.get());
			return employeeDto;
		} else {
			throw new EmployeeNotFoundException(CommonMessagesConstants.EmployeeNotFoundException);
		}
	}

}
