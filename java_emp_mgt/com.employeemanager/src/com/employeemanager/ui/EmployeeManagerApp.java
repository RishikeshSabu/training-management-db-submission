package com.employeemanager.ui;

import com.employeemanager.controller.EmployeeManagerController;
import com.employeemanager.dto.Response;

import java.util.ArrayList;

public class EmployeeManagerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath="C:/Users/VICTUS/Desktop/training-management-db-submission/java_emp_mgt/employees.csv";
		Response<ArrayList<String>> result=EmployeeManagerController.importEmployeestoDb(filepath);
		if (result.isSuccess()) {
            System.out.println("Details:");
            for (String msg : result.getData()) {
                System.out.println("  - " + msg);
            }
        } else {
            System.out.println("Failed to import: " + result.getErrorMessage());
        }
	}

}
