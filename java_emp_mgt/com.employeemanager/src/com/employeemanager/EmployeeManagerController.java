package com.employeemanager;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class EmployeeManagerController {
	
	Connection con;
	private static final String csvFile="C:/Users/VICTUS/Desktop/training-management-db-submission/java_emp_mgt/employees.csv";
	private static final String url="jdbc:mysql://localhost:3306/emp_mgt";
	private static final String user="root";
	private static final String password="Rishi@2002";
	
	//EmployeeValidator empValid=new EmployeeValidator();
	
	public ArrayList<Employee> readCSV(){
		ArrayList<Employee> empList=new ArrayList<>();
		try(BufferedReader file=new BufferedReader(new FileReader(csvFile))) {
			file.readLine();
			String line;
			while((line=file.readLine())!=null) {
				String[] datas=line.split(",");
				int emp_id=Integer.parseInt(datas[0]);
				Employee emp=new Employee(emp_id,datas[1],datas[2],datas[3],datas[4],datas[5],datas[6],datas[7]);
				System.out.println(datas[5]);
				empList.add(emp);
				
			}
		}catch(IOException e) {
			System.out.println("failed reading"+e.getMessage());
		}
		return empList;
	}
	
	public Connection establishConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
		
	}
	
	public Response InsertIntoDb(int emp_id,String firstName,String lastName,String email,String phone,String department,String salary,String joinDate) {
//		EmployeeValidator empValid=new EmployeeValidator();
		String result=EmployeeValidator.validateEmployee(emp_id, firstName, lastName, email, phone, department, salary, joinDate);
		if (result!="Valid") return new Response(false,result);
		String sql="INSERT INTO employee (emp_id,first_name,last_name,email,phone,department,salary,join_date) VALUES (?,?,?,?,?,?,?,?)";
		try(PreparedStatement st=con.prepareStatement(sql)){
			st.setInt(1, emp_id);
            st.setString(2, firstName);
            st.setString(3, lastName);
            st.setString(4, email);
            st.setString(5, phone);
            st.setString(6, department);
            st.setString(7, salary);
            st.setString(8, joinDate);
            st.executeUpdate();
            
            return new Response(true,emp_id);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return new Response(false,e.getMessage());
		}
	}
	public void closeConnection() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}