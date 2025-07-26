package com.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	Connection con;
	private static final String url="jdbc:mysql://localhost:3306/emp_mgt";
	private static final String user="root";
	private static final String password="Rishi@2002";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
}
