package com.employeemanager;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeManagerController manager=new EmployeeManagerController();
		try {
			manager.con=manager.establishConnection();
			ArrayList<Employee> employees=manager.readCSV();
			
			for(Employee emp:employees) {
				Response res=manager.InsertIntoDb(emp.emp_id, emp.first_name, emp.last_name, emp.email, emp.phone, emp.department, emp.salary, emp.join_date);
				if (res.isSuccess()) {
	                System.out.println("Inserted employee ID: " + emp.emp_id);
	            }else {
                    System.out.println("Failed to insert employee ID: " + emp.emp_id + " | Reason: " + res.getMessage());
                }
			}
			
			//System.out.println("Data Successfully inserted");
		}catch(SQLException e) {
			System.out.println("failed in connection"+e.getMessage());
		}finally {
			manager.closeConnection();
		}
	}

}
