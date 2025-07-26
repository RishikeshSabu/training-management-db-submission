package com.employeemanager.constant;

public class Constants {
	private Constants() {}
	
	public static final String INSERTEMPLOYEE="INSERT INTO employee (emp_id,first_name,last_name,email,phone,department,salary,join_date) VALUES (?,?,?,?,?,?,?,?)";
	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE emp_id = ?";
    public static final String GET_ALL_EMPLOYEES = "SELECT * FROM employees";
}
