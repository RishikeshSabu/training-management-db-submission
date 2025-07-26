package com.employeemanager.controller;

import java.util.ArrayList;

import com.employeemanager.dto.Response;
import com.employeemanager.service.EmployeeManagerService;

public class EmployeeManagerController {
	public static Response<ArrayList<String>> importEmployeestoDb(String filepath){
		if(filepath==null) return Response.failure("Filepath cannot be empty");
		return EmployeeManagerService.loadAndSavetoDb(filepath);
	}
	
}
