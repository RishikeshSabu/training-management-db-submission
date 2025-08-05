package com.employeemanager.service;


import com.employeemanager.util.ReadCSVFile;
import com.employeemanager.dao.EmployeeDao;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.util.Validation;
import com.employeemanager.exceptions.EmployeeServiceException;
import com.employeemanager.exceptions.EmployeeNotFoundException;
import com.employeemanager.exceptions.EmployeeDaoException;

import java.util.ArrayList;

public class EmployeeManagerService {
	public EmployeeDao dao=new EmployeeDao();
	public int loadAndSavetoDb(String filepath) throws EmployeeServiceException{
		
		
		int rowsInserted=0;
		ArrayList<String[]> records=new ArrayList<>();
		try {
			records=ReadCSVFile.readCSV(filepath);
		}catch(Exception e) {
			//throw new EmployeeServiceException("Error in reading the file",e);
			return 0;
		}
		for (String[] record : records) {
			try {
				String firstNameValidator=Validation.firstNameValidator(record[1]);
				String lastNameValidator=Validation.lastNameValidator(record[2]);
				String emailValidator = Validation.emailValidator(record[3]);
			    String phoneValidator = Validation.phoneValidator(record[4]);
			    String departmentValidator = Validation.departmentValidator(record[5]);
			    String joinDateValidator = Validation.joinDateValidator(record[7]);
			    if (!firstNameValidator.equals("Valid") ||
			    	    !lastNameValidator.equals("Valid") ||
			    	    !emailValidator.equals("Valid") ||
			    	    !phoneValidator.equals("Valid") ||
			    	    !departmentValidator.equals("Valid") ||
			    	    !joinDateValidator.equals("Valid")) {

			    	    continue;
			    	}
				EmployeeDTO employee=new EmployeeDTO(Integer.parseInt(record[0]),record[1],record[2],record[3],record[4],record[5],record[6],record[7]);
				
				if(dao.isEmployeeExist(employee.getEmp_id())) continue;
				dao.saveEmployee(employee);
				rowsInserted++;
				
			}catch(EmployeeNotFoundException e) {
				throw new EmployeeServiceException("Employee doesnt exist",e);
				//return 0; 
				
			}catch(EmployeeDaoException e) {
				throw new EmployeeServiceException("Failed to save employee",e);
			}
			
		}
		return rowsInserted;
		
						
	}
	public ArrayList<EmployeeDTO> getAllEmployees() throws EmployeeServiceException{
		try {
		return dao.getAllEmployees();
		}catch(EmployeeDaoException e) {
			throw new EmployeeServiceException("Failed to fetch All the employees",e);
		}
	}
}
