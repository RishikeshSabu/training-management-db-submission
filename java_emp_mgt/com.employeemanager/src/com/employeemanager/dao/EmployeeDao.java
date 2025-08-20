package com.employeemanager.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.employeemanager.dto.EmployeeDTO;

import com.employeemanager.util.DatabaseConnector;
import com.employeemanager.constant.Constants;
import com.employeemanager.exceptions.EmployeeDaoException;
import com.employeemanager.util.LoadErrorMessage;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;



public class EmployeeDao {
	private static final Logger logger = LogManager.getLogger(EmployeeDao.class);
	public boolean saveEmployee(EmployeeDTO employee) throws EmployeeDaoException {
		logger.trace("saveEmployee function executing for Employee Id {}",employee.getEmp_id());
	    try(Connection con=DatabaseConnector.getConnection()){
	    	logger.info("Database connection established successfully.");
	    	String query=Constants.INSERTEMPLOYEE;
	    	try(PreparedStatement st=con.prepareStatement(query)){
	    		logger.debug("Executing query: {} with emp_id: {}", query, employee.getEmp_id());
	    		st.setInt(1, employee.getEmp_id());
	    		st.setString(2, employee.getFirst_name());
	    		st.setString(3, employee.getLast_name());
	    		st.setString(4, employee.getEmail());
	    		st.setString(5, employee.getPhone());
	    		st.setString(6, employee.getDepartment());
	    		st.setString(7, employee.getSalary());
	    		st.setString(8, employee.getJoin_date());
	    		int rowsInserted=st.executeUpdate();
	    		logger.info("Inserted employee with id {}: rows inserted = {}", employee.getEmp_id(), rowsInserted);
	    		logger.trace("Exiting saveEmployee");
	    		return rowsInserted>0;
	    	}
	    }
	    catch (SQLException e) {
	    	logger.error("Error saving employee with id {}", employee.getEmp_id(), e);
	    	throw new EmployeeDaoException("Emp-1",e);
	    	
	    }
	}
	public List<EmployeeDTO> getAllEmployees() throws EmployeeDaoException{
		logger.trace("Entering getAllEmployees");
		List<EmployeeDTO> employees=new ArrayList<>();
		String selectQuery=Constants.GET_ALL_EMPLOYEES;
		logger.debug("Executing query: {}", selectQuery);
		try(Connection con=DatabaseConnector.getConnection();
				PreparedStatement st=con.prepareStatement(selectQuery);
				ResultSet rs=st.executeQuery(selectQuery)){
			logger.debug("Starting to iterate through resultset");
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
			logger.info("Fetched {} employees", employees.size());
            logger.trace("Exiting getAllEmployees");
			return employees;
		}catch(SQLException e) {
			logger.error("Error in fetching employees",e);
			throw new EmployeeDaoException("Emp-2",e);
			//return employees;
		}
		
	}
	
	public EmployeeDTO getEmployeeById(int employeeId) throws EmployeeDaoException {
		logger.trace("Entering getEmployeeById with employee id : {}", employeeId);
		
		try(Connection connection=DatabaseConnector.getConnection();
				PreparedStatement statement=connection.prepareStatement(Constants.SELECT_EMPLOYEE_BY_ID)){
			statement.setInt(1, employeeId);
			logger.debug("Executing query: {}", Constants.SELECT_EMPLOYEE_BY_ID);
			try(ResultSet rs=statement.executeQuery()){
				if(rs.next()) {
					EmployeeDTO employee= new EmployeeDTO(rs.getInt("emp_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("phone"),rs.getString("department"),rs.getString("salary"),rs.getString("join_date"));
					logger.info("Found the employee with employee Id {}", employeeId);
					logger.trace("Exiting getEmployeeById");
					return employee;
				}else return null;
			}
			
		}catch(SQLException e) {
			logger.error("Error in getting the detail of empployee with employee id {}",employeeId,e);
			throw new EmployeeDaoException("Emp-3",e);
		}
	}
	
	public boolean updateEmployee(EmployeeDTO employee) throws EmployeeDaoException{
		logger.trace("Entering updateEmployee for employee id: {}", employee.getEmp_id());
		try(Connection connection=DatabaseConnector.getConnection();
				PreparedStatement statement=connection.prepareStatement(Constants.UPDATE_EMPLOYEE)){
			statement.setString(1,employee.getFirst_name());
			statement.setString(2,employee.getLast_name());
			statement.setString(3,employee.getEmail());
			statement.setString(4,employee.getPhone());
			statement.setString(5,employee.getDepartment());
			statement.setString(6,employee.getSalary());
			statement.setString(7,employee.getJoin_date());
			statement.setInt(8,employee.getEmp_id());
			logger.debug("Executing update for employee id {}", employee.getEmp_id());
			int rowsUpdated=statement.executeUpdate();
			logger.info("Updated employee id {}: rows updated = {}", employee.getEmp_id(), rowsUpdated);
            logger.trace("Exiting updateEmployee");
			return rowsUpdated>0;
		}catch(SQLException e) {
			logger.error("Error updating employee with id {}", employee.getEmp_id(), e);
			throw new EmployeeDaoException("Emp-4",e);
		}
	}
	public boolean deleteEmployee(int employeeId) throws EmployeeDaoException{
		logger.trace("Entering deleteEmployee for id: {}", employeeId);
		try(Connection connection=DatabaseConnector.getConnection();
				PreparedStatement statement=connection.prepareStatement(Constants.DELETE_EMPLOYEE)){
			statement.setInt(1, employeeId);
			logger.debug("Executing delete for employee id: {}", employeeId);
			int rowsDeleted=statement.executeUpdate();
			logger.info("Deleted employee id {}", employeeId);
            logger.trace("Exiting deleteEmployee");
			return rowsDeleted>0;
		}catch(SQLException e) {
			logger.error("Error deleting employee with id {}", employeeId, e);
			throw new EmployeeDaoException("Emp-5",e);
		}
	}
	public int[] addEmployeesInBatch(List<EmployeeDTO> employeeList) throws EmployeeDaoException{
		logger.trace("Entering addEmployeesInBatch with {} employees", employeeList.size());
		try(Connection connection=DatabaseConnector.getConnection();
				PreparedStatement statement=connection.prepareStatement(Constants.INSERTEMPLOYEE)){
			logger.debug("Starting to add {} employees to batch", employeeList.size());
			for(EmployeeDTO employee:employeeList) {
				statement.setInt(1, employee.getEmp_id());
				statement.setString(2, employee.getFirst_name());
	    		statement.setString(3, employee.getLast_name());
	    		statement.setString(4, employee.getEmail());
	    		statement.setString(5, employee.getPhone());
	    		statement.setString(6, employee.getDepartment());
	    		statement.setString(7, employee.getSalary());
	    		statement.setString(8, employee.getJoin_date());
	    		logger.debug("Adding to batch employee id: {}", employee.getEmp_id());
	    		statement.addBatch();
			}
			
			int[] results= statement.executeBatch();
			logger.info("Batch insert completed");
            logger.trace("Exiting addEmployeesInBatch");
            return results;
		}catch(SQLException e) {
			//System.out.println(e.getMessage());
			logger.error("Failed to insert batch", e);
			throw new EmployeeDaoException("Emp-10",e);
		}
	}
	public List<Integer> transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeDaoException{
		 logger.trace("Entering transferEmployeesToDepartment for {} employees to department '{}'", employeeIds.size(), newDepartment);
		 List<Integer> updatedIds = new ArrayList<>();
		try(Connection connection=DatabaseConnector.getConnection()){
			connection.setAutoCommit(false);
			try(PreparedStatement statement=connection.prepareStatement(Constants.UPDATE_DEPARTMENT)){
				logger.debug("Starting batch transfer of employees to department '{}'", newDepartment);
				 for (int employeeId : employeeIds) {
		                statement.setString(1, newDepartment);
		                statement.setInt(2, employeeId);
		                logger.debug("Adding to batch transfer employee id: {} to department: {}", employeeId, newDepartment);
		                statement.addBatch();
		            }
				 int[] results=statement.executeBatch();
				 int index=0;
				 for (int result : results) {
		                if (result == 0) {
		                    connection.rollback();
		                    logger.error("Update failed for employee id {}. Transaction rolled back.", employeeIds.get(index));
		                    throw new EmployeeDaoException("Emp-4");
		                }else {
		                	updatedIds.add(employeeIds.get(index));
		                	index++;
		                }
		                
		            }
				 connection.commit();
				 logger.info("Transferred {} employees to department '{}'", updatedIds.size(), newDepartment);
	             logger.trace("Exiting transferEmployeesToDepartment");
				 return updatedIds;
			}catch (SQLException e) {
	            connection.rollback();
	            logger.error("Error transferring employees to department '{}'", newDepartment, e);
	            System.out.println("Problem");
	            throw new EmployeeDaoException("Emp-11",e);
			}
		}catch(SQLException e) {
			logger.error("Database error during transferEmployeesToDepartment", e);
			throw new EmployeeDaoException("Emp-9",e);
		}
		
	}
	
}
