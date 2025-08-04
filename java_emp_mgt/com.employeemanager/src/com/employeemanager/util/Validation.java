package com.employeemanager.util;

import com.employeemanager.constant.Constants;

public class Validation {
	public static String firstNameValidator(String firstName){
		if (firstName == null || firstName.trim().isEmpty()) {
            return "First name is missing";
        }
		return "Valid";
	}
	public static String lastNameValidator(String lastName) {
		if (lastName == null || lastName.trim().isEmpty()) {
            return "Last name is missing";
        }
		return "Valid";
	}
	public static String emailValidator(String email) {
		if (email==null || !email.matches(Constants.EMAIL_REGEX)) return "invalid email format";
		return "Valid";
	}
	public static String phoneValidator(String phone) {
		if (phone==null || !phone.matches(Constants.PHONE_REGEX)) return "invalid Phone NUmber";
		return "Valid";
	}
	public static String departmentValidator(String department) {
		if (department==null || department.trim().isEmpty()) return "Department is not given";
		return "Valid";
	}
	public static String joinDateValidator(String joinDate) {
		if (joinDate == null || !joinDate.matches(Constants.DATE_REGEX)) return "Join date must be in format yyyy-mm-dd";
		return "Valid";
	}
}
