package com.employeemanager.dao;

import com.employeemanager.dto.Response;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.util.Validation;
import com.employeemanager.util.DatabaseConnector;
import com.employeemanager.constant.Constants;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;


public class EmployeeDao {
	public static Response<Integer> insertIntoDB(EmployeeDTO employee) {
		String firstNameValidator=Validation.firstNameValidator(employee.getFirst_name());
		String lastNameValidator = Validation.lastNameValidator(employee.getLast_name());
	    String emailValidator = Validation.emailValidator(employee.getEmail());
	    String phoneValidator = Validation.phoneValidator(employee.getPhone());
	    String departmentValidator = Validation.departmentValidator(employee.getDepartment());
	    String joinDateValidator = Validation.joinDateValidator(employee.getJoin_date());
	    if (!firstNameValidator.equals("Valid") ||
	    	    !lastNameValidator.equals("Valid") ||
	    	    !emailValidator.equals("Valid") ||
	    	    !phoneValidator.equals("Valid") ||
	    	    !departmentValidator.equals("Valid") ||
	    	    !joinDateValidator.equals("Valid")) {

	    	    return Response.<Integer>failure("Validation failed");
	    	}
	    try(Connection con=DatabaseConnector.getConnection()){
	    	String query=Constants.INSERTEMPLOYEE;
	    	try(PreparedStatement st=con.prepareStatement(query)){
	    		st.setInt(1, employee.getEmp_id());
	    		st.setString(2, employee.getFirst_name());
	    		st.setString(3, employee.getLast_name());
	    		st.setString(4, employee.getEmail());
	    		st.setString(5, employee.getPhone());
	    		st.setString(6, employee.getDepartment());
	    		st.setString(7, employee.getSalary());
	    		st.setString(8, employee.getJoin_date());
	    		st.executeUpdate();
	    		return Response.success(employee.getEmp_id());
	    	
	    	}
	    }catch(SQLIntegrityConstraintViolationException e) {
	    	 return Response.failure("Duplicate entry: " + e.getMessage());
	    }
	    catch (SQLException e) {
	        e.printStackTrace(); // Or use a logger
	        return Response.failure("Database error: " + e.getMessage());
	    }
	}
	public static Response<ArrayList<EmployeeDTO>> getAllEmployee(){
		ArrayList<EmployeeDTO> empList=new ArrayList<>();
		String selectQuery=Constants.GET_ALL_EMPLOYEES;
		try(Connection con=DatabaseConnector.getConnection();
				PreparedStatement st=con.prepareStatement(selectQuery);
				ResultSet rs=st.executeQuery(selectQuery)){
			while(rs.next()) {
				int emp_id=rs.getInt("emp_id");
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("lastname");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String department=rs.getString("department");
				String salary=rs.getString("salary");
				String joinDate=rs.getString("join_date");
				EmployeeDTO emp=new EmployeeDTO(emp_id,firstName,lastName,email,phone,department,salary,joinDate);
				empList.add(emp);
			}
			return Response.success(empList);
		}catch(SQLException e) {
			return Response.failure("Error : "+e.getMessage());
		}
		
	}
}
