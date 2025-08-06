package com.employeemanager.service;


import com.employeemanager.util.ReadCSVFile;
import com.employeemanager.constant.Constants;
import com.employeemanager.dao.EmployeeDao;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.util.Validation;
import com.employeemanager.exceptions.EmployeeServiceException;
import com.employeemanager.exceptions.EmployeeNotFoundException;
import com.employeemanager.exceptions.EmployeeDaoException;
import com.employeemanager.exceptions.CSVFileAccessException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerService {
	public EmployeeDao dao=new EmployeeDao();
	public int loadAndSavetoDb(String filepath) throws EmployeeServiceException{
		
		
		int rowsInserted=0;
		List<String[]> records=new ArrayList<>();
		try {
			records=ReadCSVFile.readCSV(filepath);
			/* to know error
			System.out.println("Total records read: " + records.size());
			*/
		}catch(CSVFileAccessException e) {
			throw new EmployeeServiceException(Constants.CSV_ERROR,e);
			//return 0;
		}
		for (String[] record : records) {
			try {
				String firstNameValidator=Validation.firstNameValidator(record[1]);
				String lastNameValidator=Validation.lastNameValidator(record[2]);
				String emailValidator = Validation.emailValidator(record[3]);
			    String phoneValidator = Validation.phoneValidator(record[4]);
			    String departmentValidator = Validation.departmentValidator(record[5]);
			    
			    if (!firstNameValidator.equals("Valid") ||
			    	    !lastNameValidator.equals("Valid") ||
			    	    !emailValidator.equals("Valid") ||
			    	    !phoneValidator.equals("Valid") ||
			    	    !departmentValidator.equals("Valid") 
			    	    ) {
			    	
			    	    continue;
			    	}
				EmployeeDTO employee=new EmployeeDTO(Integer.parseInt(record[0]),record[1],record[2],record[3],record[4],record[5],record[6],record[7]);
				
				if(dao.isEmployeeExist(employee.getEmp_id())) continue;
				dao.saveEmployee(employee);
				rowsInserted++;
				
			}catch(EmployeeNotFoundException e) {
				throw new EmployeeServiceException(Constants.NO_EMPLOYEE_ERROR,e);
				//return 0; 
				
			}catch(EmployeeDaoException e) {
				throw new EmployeeServiceException(Constants.SAVE_EMPLOYEE_ERROR,e);
			}
			
		}
		return rowsInserted;
		
						
	}
	public ArrayList<EmployeeDTO> getAllEmployees() throws EmployeeServiceException{
		try {
		return dao.getAllEmployees();
		}catch(EmployeeDaoException e) {
			throw new EmployeeServiceException(Constants.EMPLOYEE_FETCH_ERROR,e);
		}
	}
}
