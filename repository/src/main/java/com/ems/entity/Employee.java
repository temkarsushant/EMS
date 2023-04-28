package com.ems.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long UUID;

	private String firstName;

	private String lastName;

	private String mobileNumber;

	private String grade = "T1";

	private String salary;

	private String dateOfBirth;

	private Boolean audit;

	public Employee(String firstName, String lastName, String mobileNumber, String grade, String salary,
			String dateOfBirth, Boolean audit) {
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
