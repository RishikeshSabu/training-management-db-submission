package com.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;


public class DatabaseConnector {
	
	private static String url;
	private static String user;
	private static String password;
	Connection con;
//	private static final String url="jdbc:mysql://localhost:3306/emp_mgt";
//	private static final String user="root";
//	private static final String password="Rishi@2002";
	static {
		try {
			Properties props = new Properties();
            InputStream input = DatabaseConnector.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("Cannot find config.properties file");
            }
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.username");
            password = props.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
}
