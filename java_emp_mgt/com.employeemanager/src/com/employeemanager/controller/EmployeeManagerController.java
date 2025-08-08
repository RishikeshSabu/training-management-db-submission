package com.employeemanager.controller;

import java.util.ArrayList;
import java.util.List;

import com.employeemanager.dto.Response;
import com.employeemanager.service.EmployeeManagerService;
import com.employeemanager.constant.Constants;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.exceptions.EmployeeServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeManagerController {
	private static final Logger logger=LogManager.getLogger(EmployeeManagerController.class);
	private EmployeeManagerService service=new EmployeeManagerService();
	
	public Response<String> importEmployeestoDb(String filepath){
		logger.trace("Entering importEmployeestoDb with filepath: {}", filepath);
		int rowsInserted;
		if(filepath==null||filepath.trim().isEmpty()) {
			logger.warn("Filepath is null or empty");
			return new Response<>(400,Constants.NO_FILE);
		}
		try {
		if(!filepath.toLowerCase().endsWith(".csv")) {
			logger.warn("Invalid file extension for filepath: {}", filepath);
			return new Response<>(400,Constants.NOT_CSV);
		}
		rowsInserted=service.loadAndSavetoDb(filepath);
		logger.info("Imported {} employees from file: {}", rowsInserted, filepath);
        logger.trace("Exiting importEmployeestoDb");
		return new Response<>(200,null,String.format(Constants.INSERT_SUCCESS,rowsInserted));
		}catch(EmployeeServiceException e) {
			logger.error("Error importing employees from file: {}", filepath, e);
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			logger.error("Unexpected error importing employees from file: {}", filepath, e);
			return new Response<>(400,e.getMessage());
		}
		
	}
	
	public Response<List<EmployeeDTO>> getAllEmployees(){
		logger.trace("Entering getAllEmployees");
		try {
		List<EmployeeDTO> employees=service.getAllEmployees();
		if(employees==null) {
			logger.error("getAllEmployees returned null");
			return new Response<>(500,Constants.SERVER_ERROR);
		}
		logger.info("Fetched {} employees", employees.size());
        logger.trace("Exiting getAllEmployees");
		return new Response<>(200,employees);
		}catch(EmployeeServiceException e) {
			logger.error("Error fetching all employees", e);
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			logger.error("Unexpected error in fetching employees",e);
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<Integer> addEmployee(EmployeeDTO employee){
		logger.trace("Entering addEmployee with employee Id{}",employee.getEmp_id());
		try {
			if(employee==null) {
				logger.warn("addEmployee called with null employee");
				return new Response<>(400,Constants.NULL_ERROR);
			}
			//if(service.getEmployeebyId(employee.getEmp_id())!=null) return new Response<>(400,Constants.EMPLOYEE_EXIST);
			boolean result=service.addEmployee(employee);
			logger.info("Employee added with id: {}", employee.getEmp_id());
            logger.trace("Exiting addEmployee");
			int employeeId=employee.getEmp_id();
			return new Response<>(200,employeeId);
		}catch(EmployeeServiceException e) {
			logger.error("Failed to add employee with id: {}", employee.getEmp_id(), e);
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			logger.error("Unexpected error adding employee", e);
			return new Response<>(400,e.getMessage());
		}
	}
		
	public Response<EmployeeDTO> getEmployeeById(int employeeId){
		logger.trace("Entering getEmployeeById with id: {}", employeeId);
		try {
			EmployeeDTO employee=service.getEmployeebyId(employeeId);
			logger.info("Found employee with id: {}", employeeId);
            logger.trace("Exiting getEmployeeById");
			return new Response<>(200,employee);
		}catch(EmployeeServiceException e) {
			logger.error("Error fetching employee by id: {}", employeeId, e);
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<Integer> updateEmployee(EmployeeDTO employee){
		logger.trace("Entering updateEmployee for employee Id {}", employee.getEmp_id());
		try {
			service.updateEmployee(employee);
			logger.info("Updated employee with id: {}", employee.getEmp_id());
            logger.trace("Exiting updateEmployee");
			return new Response<>(200,employee.getEmp_id());
		}catch(EmployeeServiceException e){
			logger.error("Failed to update employee with id: {}", employee != null ? employee.getEmp_id() : null, e);
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<Integer> deleteEmployee(int employeeId){
		logger.trace("Entering deleteEmployee with id: {}", employeeId);
		try {
			service.deleteEmployee(employeeId);
			logger.info("Deleted employee with id: {}", employeeId);
            logger.trace("Exiting deleteEmployee");
			return new Response<>(200,employeeId);
		}catch(EmployeeServiceException e) {
			logger.error("Failed to delete employee with id: {}", employeeId, e);
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			logger.error("Unexpected error occured in deleteing the employee with id {}",employeeId,e);
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<List<String>> addEmployeesInBatch(List<EmployeeDTO> employeeList){
		logger.trace("Entering addEmployeesInBatch with {} employees",employeeList.size());
		try {
			if (employeeList == null || employeeList.isEmpty()) {
				logger.warn("addEmployeesInBatch called with null or empty list");
	            return new Response<>(400, Constants.EMPTY_EMPLOYEE);
	        }
			List<String> employeeAddedResult=new ArrayList<>();
			int[] results=service.addEmployeesInBatch(employeeList);
			for (int i = 0; i < results.length; i++) {
	            if (results[i] == 1) {
	            	employeeAddedResult.add("Employee " + employeeList.get(i).getEmp_id() + " added successfully.");
	            } else {
	            	employeeAddedResult.add("Employee " + employeeList.get(i).getEmp_id() + " failed to add.");
	            }
	        }
			logger.info("Batch addEmployeesInBatch completed with {} employees", employeeList.size());
            logger.trace("Exiting addEmployeesInBatch");
			return new Response<>(200,employeeAddedResult);
		}catch(EmployeeServiceException e) {
			logger.error("Failed during batch addEmployeesInBatch", e);
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			logger.error("Unexpected error occured in adding {} employees to batch",employeeList.size(),e);
			return new Response<>(400,e.getMessage());
		}
		
	}
	
	public Response<List<Integer>> transferEmployeesToBatch(List<Integer> employeeIds,String newDepartment){
		logger.trace("Entering transferEmployeesToBatch for {} employees to department '{}'",employeeIds.size(),newDepartment);
		try {
			if(employeeIds==null||employeeIds.isEmpty()) {
				logger.warn("transferEmployeesToBatch called with null or empty employeeIds");
				return new Response<>(400,Constants.EMPTY_EMPLOYEE);
			}
			List<Integer> updatedIds=service.transferEmployeesToDepartment(employeeIds, newDepartment);
			logger.info("Transferred {} employees to department '{}'", updatedIds.size(), newDepartment);
            logger.trace("Exiting transferEmployeesToBatch");
			return new Response<>(200,updatedIds);
		}catch(EmployeeServiceException e) {
			logger.error("Failed to transfer employees to department '{}'", newDepartment, e);
			return new Response<>(400,Constants.UPDATION_FAILED);
		}catch(Exception e) {
			logger.error("Unxpected error occured in updating the department of employees", e);
			return new Response<>(400,e.getMessage());
		}
	}
	
}
