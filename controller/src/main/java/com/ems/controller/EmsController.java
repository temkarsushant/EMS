package com.ems.controller;

import java.util.List;

import javax.validation.Valid;

import com.ems.constants.CommonMessagesConstants;
import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.InvalidGradeException;
import com.ems.customexception.SaveEmployeeDetailsException;
import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.service.EmsService;
import com.ems.utility.CommonUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ems/api")
public class EmsController {

	@Autowired
	EmsService emsService;

	@Autowired
	CommonUtility commonUtility;

	@PostMapping("/v1")
	public ResponseEntity<EmployeeDto> saveEmployee(@Valid @RequestBody EmployeeDto employee)
			throws InvalidAgeException, InvalidGradeException, SaveEmployeeDetailsException {
		if (CommonUtility.findByGrade(employee.getGrade())) {
			return new ResponseEntity<>(emsService.save(employee), HttpStatus.OK);
		} else {
			throw new InvalidGradeException(CommonMessagesConstants.InvalidGradeExceptionConstant);
		}
	}

	@GetMapping("/v1")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeesList() throws EmployeeNotFoundException {
		return new ResponseEntity<>(emsService.getAllEmployeesList(), HttpStatus.OK);
	}

	@GetMapping("/v1/{eid}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("eid") Long employeeId)
			throws EmployeeNotFoundException {
		return new ResponseEntity<>(emsService.getEmployee(employeeId), HttpStatus.OK);
	}

}
