package com.employeemanager.util;

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
		if (email==null || !email.matches("^\\S+@\\S+\\.\\S+$")) return "invalid email format";
		return "Valid";
	}
	public static String phoneValidator(String phone) {
		if (phone==null || !phone.matches("^\\d{10}$")) return "invalid Phone NUmber";
		return "Valid";
	}
	public static String departmentValidator(String department) {
		if (department==null || department.trim().isEmpty()) return "Department is not given";
		return "Valid";
	}
	public static String joinDateValidator(String joinDate) {
		if (joinDate == null || !joinDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) return "Join date must be in format yyyy-mm-dd";
		return "Valid";
	}
}
