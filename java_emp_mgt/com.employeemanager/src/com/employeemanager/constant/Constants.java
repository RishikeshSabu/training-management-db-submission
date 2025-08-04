package com.employeemanager.constant;

public class Constants {
	private Constants() {}
	
	public static final String INSERTEMPLOYEE="INSERT INTO employee (emp_id,first_name,last_name,email,phone,department,salary,join_date) VALUES (?,?,?,?,?,?,?,?)";
	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT emp_id,first_name,last_name,phone FROM employee WHERE emp_id = ?";
    public static final String GET_ALL_EMPLOYEES = "SELECT * FROM employee";
    
    public static final String EMAIL_REGEX="^\\S+@\\S+\\.\\S+$";
    public static final String PHONE_REGEX="^\\d{10}$";
    public static final String DATE_REGEX="^\\\\d{4}-\\\\d{2}-\\\\d{2}$";
}
