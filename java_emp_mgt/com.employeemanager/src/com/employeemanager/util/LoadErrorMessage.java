package com.employeemanager.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadErrorMessage {
	private static Properties props=new Properties();
	static {
		try(FileReader reader=new FileReader("C:/Users/VICTUS/Desktop/training-management-db-submission/java_emp_mgt/com.employeemanager/src/resources/errorMessages.properties")){
			props.load(reader);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static String getErrorMessage(String errorCode) {
		return props.getProperty(errorCode);
	}
}
