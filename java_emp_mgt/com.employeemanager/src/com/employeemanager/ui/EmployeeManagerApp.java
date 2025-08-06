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
//		
		//getAllEmployee
		Response<ArrayList<EmployeeDTO>> employeesResponse=controller.getAllEmployees();
		if(employeesResponse.getStatusCode()==200) {
			ArrayList<EmployeeDTO> employees=employeesResponse.getData();
			for(EmployeeDTO employee:employees) {
				System.out.println(employee);
			}
			}else {
				System.out.println("Error message : "+employeesResponse.getErrorMessage());
			}
		
		//addEmployee
		//EmployeeDTO employee=new EmployeeDTO(101, "Rahul", "John", "rahul.john@example.com", "9123456789", "Engineering", "75000", "2021-06-15");-->Duplicate Key error
		//EmployeeDTO employee=null;-->null Exception
		//EmployeeDTO employee=new EmployeeDTO(108, "Vaibhav", "Narayanan", "Vaibhav@example.com", "73563023456", "Engineering", "40000", "2021-06-13");-->Invalid credential(11 digit phone)
		EmployeeDTO employee=new EmployeeDTO(108, "Vaibhav", "Narayanan", "Vaibhav@example.com", "7356302341", "Engineering", "40000", "2021-06-13");
		Response<Integer> addEmployeeResponse=controller.addEmployee(employee);
		if(addEmployeeResponse.getStatusCode()==200) {
			System.out.println("Employer with employee_id "+addEmployeeResponse.getData()+" inserted succesfully");
		}else {
			System.out.println("Error : "+addEmployeeResponse.getErrorMessage());
		}
//		
//		//getEmployeeById
//		//int employeeId=10;-->empId doesnt exist
		int employeeId=101;
		Response<EmployeeDTO> employeeResponse=controller.getEmployeeById(employeeId);
		if(employeeResponse.getStatusCode()==200) System.out.println(employeeResponse.getData());
		else System.out.println("Error : "+employeeResponse.getErrorMessage());
		
		//updateEmployee
		EmployeeDTO updateEmployee=new EmployeeDTO(101, "Akshay", "John", "akshayjohn@example.com", "8129457890", "Engineering", "75000", "2021-06-15");
		//EmployeeDTO updateEmployee=new EmployeeDTO(10, "Akshay", "John", "akshayjohn@example.com", "8129457890", "Engineering", "75000", "2021-06-15");-->emp_id doesnt exist exception
		Response<Integer> updateEmployeeResponse=controller.updateEmployee(updateEmployee);
		if(updateEmployeeResponse.getStatusCode()==200) System.out.println("updated Successfully");
		else System.out.println(updateEmployeeResponse.getErrorMessage());
		
		//deleteEmployee
		//int deleteEmployeeId=10-->AlreadyExistError
		int deleteEmployeeId=101;
		Response<Integer> deleteEmployeeResponse=controller.deleteEmployee(deleteEmployeeId);
		if(deleteEmployeeResponse.getStatusCode()==200) System.out.println("successfully deleted the employer");
		else System.out.println(deleteEmployeeResponse.getErrorMessage());
		
		
	}
	
	
}
