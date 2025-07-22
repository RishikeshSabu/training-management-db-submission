package com.employeemanager;

public class EmployeeValidator {
	public static String validateEmployee(int emp_id,String firstName,String lastName,String email,String phone,String department,String salary,String joinDate) {
		if (firstName == null || firstName.isEmpty()) {
            return "First name is missing";
        }
		if (lastName == null || lastName.trim().isEmpty()) {
            return "Last name is missing";
        }
		if (email==null || !email.matches("^\\S+@\\S+\\.\\S+$")) return "invalid email format";
		if (phone==null || !phone.matches("^\\d{10}$")) return "invalid Phone NUmber";
		//if (department==null || !department.trim().isEmpty()) return "Department is not given";
		if (joinDate == null || !joinDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) return "Join date must be in format yyyy-mm-dd";
		return "Valid";
	}
}
