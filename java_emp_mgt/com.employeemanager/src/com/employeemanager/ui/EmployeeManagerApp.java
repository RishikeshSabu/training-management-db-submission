package com.employeemanager.ui;

import com.employeemanager.controller.EmployeeManagerController;
import com.employeemanager.dto.Response;
import com.employeemanager.dto.EmployeeDTO;
import java.util.ArrayList;

public class EmployeeManagerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeManagerController controller=new EmployeeManagerController();
		String filepath="C:/Users/VICTUS/Desktop/training-management-db-submission/java_emp_mgt/employees.csv";
		Response<String> response=controller.importEmployeestoDb(filepath);
		if(response.getStatusCode()==200) System.out.println(response.getData());
		if(response.getStatusCode()==400) System.out.println(response.getErrorMessage());
		
		
		Response<ArrayList<EmployeeDTO>> employeesResponse=controller.getAllEmployees();
		if(employeesResponse.getStatusCode()==200) {
			ArrayList<EmployeeDTO> employees=employeesResponse.getData();
			for(EmployeeDTO employee:employees) {
				System.out.println(employee);
			}
			}else {
				System.out.println("Error message : "+employeesResponse.getErrorMessage());
			}
		
	}
}
