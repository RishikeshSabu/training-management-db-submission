package com.employeemanager.controller;

import java.util.ArrayList;

import com.employeemanager.dto.Response;
import com.employeemanager.service.EmployeeManagerService;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.exceptions.EmployeeServiceException;

public class EmployeeManagerController {
	private EmployeeManagerService service=new EmployeeManagerService();
	public Response<String> importEmployeestoDb(String filepath){
		int rowsInserted;
		if(filepath==null||filepath.trim().isEmpty()) return new Response<>(400,"file not found");
		try {
		if(!filepath.toLowerCase().endsWith(".csv")) return new Response<>(400,"The file must be a csv file");
		rowsInserted=service.loadAndSavetoDb(filepath);
		return new Response<>(200,null,"Inserted "+rowsInserted+ " number of records");
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}catch(Exception e) {
			return new Response<>(400,e.getMessage());
		}
		
	}
	
	public Response<ArrayList<EmployeeDTO>> getAllEmployees(){
		try {
		ArrayList<EmployeeDTO> employees=service.getAllEmployees();
		if(employees==null) return new Response<>(500,"Internal Server Error");
		return new Response<>(200,employees);
		}catch(EmployeeServiceException e) {
			return new Response<>(400,e.getMessage());
		}
	}
		
	
}
