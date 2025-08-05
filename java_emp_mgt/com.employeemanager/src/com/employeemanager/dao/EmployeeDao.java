package com.employeemanager.dao;


import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.util.Validation;
import com.employeemanager.util.DatabaseConnector;
import com.employeemanager.constant.Constants;
import com.employeemanager.exceptions.EmployeeDaoException;
import com.employeemanager.exceptions.EmployeeNotFoundException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;



public class EmployeeDao {
	public boolean saveEmployee(EmployeeDTO employee) throws EmployeeDaoException {
		
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
	    		int rowsInserted=st.executeUpdate();
	    		return rowsInserted>0;
	    	
	    	}
	    }
	    catch (SQLException e) {
	    	throw new EmployeeDaoException("Error in saving employee",e);
	        //e.printStackTrace(); 
	        //return false;
	    }
	}
	public ArrayList<EmployeeDTO> getAllEmployees() throws EmployeeDaoException{
		ArrayList<EmployeeDTO> employees=new ArrayList<>();
		String selectQuery=Constants.GET_ALL_EMPLOYEES;
		try(Connection con=DatabaseConnector.getConnection();
				PreparedStatement st=con.prepareStatement(selectQuery);
				ResultSet rs=st.executeQuery(selectQuery)){
			while(rs.next()) {
				int emp_id=rs.getInt("emp_id");
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String department=rs.getString("department");
				String salary=rs.getString("salary");
				String joinDate=rs.getString("join_date");
				EmployeeDTO emp=new EmployeeDTO(emp_id,firstName,lastName,email,phone,department,salary,joinDate);
				employees.add(emp);
			}
			return employees;
		}catch(SQLException e) {
			throw new EmployeeDaoException("Error in fetching employees",e);
			//return employees;
		}
		
	}
	public boolean isEmployeeExist(int empId)throws EmployeeNotFoundException {
		try(Connection con=DatabaseConnector.getConnection();
				PreparedStatement statement=con.prepareStatement(Constants.SELECT_EMPLOYEE_BY_ID)){
			statement.setInt(1, empId);
			try(ResultSet rs=statement.executeQuery()){
				return rs.next();
			}
		}catch(SQLException e) {
			throw new EmployeeNotFoundException("employee doesnt Exist",e);
//			e.printStackTrace();
//			return false;
		}
	}
	
}
