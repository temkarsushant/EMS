package com.ems.utility;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ems.enums.GreadeAndDesignationEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CommonUtility {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static int calculateAge(LocalDate dob) {
		LocalDate curDate = LocalDate.now();
		if ((dob != null) && (curDate != null)) {
			return Period.between(dob, curDate).getYears();
		} else {
			return 0;
		}
	}

	public static boolean findByGrade(String name) {
		boolean result = false;
		for (GreadeAndDesignationEnum grade : GreadeAndDesignationEnum.values()) {
			if (grade.name().equalsIgnoreCase(name)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static LocalDate getLocalDate(String date) {
		return LocalDate.parse(date, formatter);
	}

	public static String getEmployeeDesignation(String grade) {

		return GreadeAndDesignationEnum.valueOf(grade).label;
	}

}
