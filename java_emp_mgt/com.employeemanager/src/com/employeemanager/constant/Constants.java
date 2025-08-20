package com.employeemanager.constant;

public class Constants {
	private Constants() {}
	
	public static final String INSERTEMPLOYEE="INSERT INTO employee (emp_id,first_name,last_name,email,phone,department,salary,join_date) VALUES (?,?,?,?,?,?,?,?)";
	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT emp_id,first_name,last_name,email,phone,department,salary,join_date FROM employee WHERE emp_id = ?";
    public static final String GET_ALL_EMPLOYEES = "SELECT * FROM employee";
    public static final String UPDATE_EMPLOYEE="UPDATE employee SET first_name=?, last_name=?, email=?, phone=?, department=?, salary=?, join_date=? WHERE emp_id=?";
    public static final String DELETE_EMPLOYEE="DELETE FROM employee WHERE emp_id=?";
    public static final String UPDATE_DEPARTMENT="UPDATE employee SET department=? WHERE emp_id=?";
    
    public static final String EMAIL_REGEX="^\\S+@\\S+\\.\\S+$";
    public static final String PHONE_REGEX="^\\d{10}$";
    public static final String DATE_REGEX="^\\\\d{4}-\\\\d{2}-\\\\d{2}$";
    
    //MessageConstant
    public static final String CSV_ERROR="Unable to read CSC file";
    public static final String SAVE_EMPLOYEE_ERROR="Error in saving employee";
    public static final String EMPLOYEE_FETCH_ERROR="Error in fetching employees";
    public static final String NO_EMPLOYEE_ERROR="employee doesn't Exist";
    public static final String NO_FILE="file not found";
    public static final String NOT_CSV="The file must be a csv file";
    public static final String UPDATION_FAILED="Failed to update the employee";
    public static final String DELETION_FAILED="Failed to delete the employee";
    public static final String INSERTION_FAILED="failed to add employee";
    public static final String SERVER_ERROR="Internal Server Error";
    //public static final String ALREADY_EXIST=
    public static final String INSERT_SUCCESS = "Inserted %d number of records";
    public static final String NULL_ERROR="Employee cannot be null";
    public static final String EMPLOYEE_EXIST="Employee id already exist";
    public static final String EMPTY_EMPLOYEE="Employee list is empty";
    public static final String EMPLOYEE_BATCH_SUCCESS= "Employee with employee id %d has been successfully added to the batch.";
    public static final String EMPLOYEE_BATCH_FAILURE= "Failed to add employee with employee id %d to the batch";
    public static final String DATABASE_ERROR="Error in database operation";
    
    //error codes
    

}
