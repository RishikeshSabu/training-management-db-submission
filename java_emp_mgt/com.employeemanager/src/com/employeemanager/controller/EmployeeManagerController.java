package com.employeemanager.controller;

import java.util.ArrayList;
import java.util.List;

import com.employeemanager.dto.Response;
import com.employeemanager.service.EmployeeManagerService;
import com.employeemanager.constant.Constants;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.exceptions.EmployeeServiceException;


public class EmployeeManagerController {
	private EmployeeManagerService service=new EmployeeManagerService();
	
	public Response<String> importEmployeestoDb(String filepath){
		int rowsInserted;
		if(filepath==null||filepath.trim().isEmpty()) return new Response<>(400,Constants.NO_FILE);
		try {
		if(!filepath.toLowerCase().endsWith(".csv")) return new Response<>(400,Constants.NOT_CSV);
		rowsInserted=service.loadAndSavetoDb(filepath);
		return new Response<>(200,null,String.format(Constants.INSERT_SUCCESS,rowsInserted));
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			return new Response<>(400,e.getMessage());
		}
		
	}
	
	public Response<List<EmployeeDTO>> getAllEmployees(){
		try {
		List<EmployeeDTO> employees=service.getAllEmployees();
		if(employees==null) return new Response<>(500,Constants.SERVER_ERROR);
		return new Response<>(200,employees);
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<Integer> addEmployee(EmployeeDTO employee){
		try {
			if(employee==null) return new Response<>(400,Constants.NULL_ERROR);
			//if(service.getEmployeebyId(employee.getEmp_id())!=null) return new Response<>(400,Constants.EMPLOYEE_EXIST);
			boolean result=service.addEmployee(employee);
			int employeeId=employee.getEmp_id();
			return new Response<>(200,employeeId);
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			return new Response<>(400,e.getMessage());
		}
	}
		
	public Response<EmployeeDTO> getEmployeeById(int employeeId){
		try {
			EmployeeDTO employee=service.getEmployeebyId(employeeId);
			return new Response<>(200,employee);
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<Integer> updateEmployee(EmployeeDTO employee){
		try {
			service.updateEmployee(employee);
			return new Response<>(200,employee.getEmp_id());
		}catch(EmployeeServiceException e){
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<Integer> deleteEmployee(int employeeId){
		try {
			
			service.deleteEmployee(employeeId);
			return new Response<>(200,employeeId);
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}
	}
	
	public Response<List<String>> addEmployeesInBatch(List<EmployeeDTO> employeeList){
		
		try {
			int index=0;
			List<String> employeeAddedResult=new ArrayList<>();
			if(employeeList==null||employeeList.isEmpty()) return new Response<>(400,Constants.EMPTY_EMPLOYEE);
			int[] results=service.addEmployeesInBatch(employeeList);
			for(int result:results) {
				if (result==1) employeeAddedResult.add(String.format(Constants.EMPLOYEE_BATCH_SUCCESS,employeeList.get(index).getEmp_id()));
				else employeeAddedResult.add(String.format(Constants.EMPLOYEE_BATCH_FAILURE,employeeList.get(index).getEmp_id()));
				index++;
			}
			return new Response<>(200,employeeAddedResult);
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}
		
	}
	
}
