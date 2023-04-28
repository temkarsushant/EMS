package com.ems.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.ems.customexception.InvalidGradeException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonUtilityTest {

//	@Autowired
//	private CommonUtility commonUtility;

	@Test
	public void testCalculateAge_shouldReturnAge_whenDateOfBirthIsPassed() {
		int expectedAge = 25;

		String dateOfBirth = "05/08/1997";
		LocalDate dateOfBirthInlocalDateFormat = CommonUtility.getLocalDate(dateOfBirth);
		int actualAge = CommonUtility.calculateAge(dateOfBirthInlocalDateFormat);
		assertEquals(expectedAge, actualAge);

	}

	@Test
	public void testCalculateAge_shouldReturnZero_whenDateOfBirthIsPassedAsNull() {
		LocalDate dateOfBirthInlocalDateFormat = null;
		int actualAge = CommonUtility.calculateAge(dateOfBirthInlocalDateFormat);
		assertEquals(0, actualAge);

	}

	@Test
	public void testFindByGrade_shouldReturnBooleanResult_whenGreadIsPassed() {
		assertTrue(CommonUtility.findByGrade("E1"));
		assertFalse(CommonUtility.findByGrade("R1"));

	}

	@Test
	public void testGetEmployeeDesignation_shouldReturnString_whenEmployeeGreadIsPassed() {
		String expectedDesignation = "Software Engg";
		String actualDesignation = CommonUtility.getEmployeeDesignation("E1");
		assertEquals(expectedDesignation, actualDesignation);
	}

	@Test
	public void testGetEmployeeDesignation_shouldReturnNull_whenWrongEmployeeGreadIsPassed() {
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			CommonUtility.getEmployeeDesignation("L1");
		});

	}

}
