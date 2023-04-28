package com.ems.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeDto {
	private String fullName;
	@NotBlank(message = "First Name is mandatory")
	private String firstName;
	@NotBlank(message = "Last Name is mandatory")
	private String lastName;
	@NotBlank(message = "Mobile Number is mandatory")
	private String mobileNumber;
	@NotBlank(message = "Grade is mandatory")
	private String grade;
	private String designation;
	@NotBlank(message = "Salary is mandatory")
	private String salary;
	@NotBlank(message = "Date Of Birth is mandatory")
	private String dateOfBirth;
	private int age;
	@NotNull
	private Boolean audit;

	public EmployeeDto(String fullName, String mobileNumber, String grade, String designation, String salary,
			String dateOfBirth, int age, Boolean audit) {
		super();
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
		this.grade = grade;
		this.designation = designation;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.audit = audit;
	}

	public EmployeeDto(@NotBlank(message = "First Name is mandatory") String firstName,
			@NotBlank(message = "Last Name is mandatory") String lastName,
			@NotBlank(message = "Mobile Number is mandatory") String mobileNumber,
			@NotBlank(message = "Grade is mandatory") String grade,
			@NotBlank(message = "Salary is mandatory") String salary,
			@NotBlank(message = "Date Of Birth is mandatory") String dateOfBirth, @NotNull Boolean audit) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.grade = grade;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.audit = audit;
	}

}
