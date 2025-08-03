package com.employeemanager.service;


import com.employeemanager.util.ReadCSVFile;
import com.employeemanager.dao.EmployeeDao;
import com.employeemanager.dto.EmployeeDTO;
import com.employeemanager.util.Validation;

import java.util.ArrayList;

public class EmployeeManagerService {
	public EmployeeDao dao=new EmployeeDao();
	public int loadAndSavetoDb(String filepath){
		
		//ArrayList<String> responseMessage=new ArrayList<>();
		//if(!filepath.toLowerCase().endsWith(".csv")) return Response.failure("The file must be a csv file");
		int rowsInserted=0;
		ArrayList<String[]> records=new ArrayList<>();
		try {
			records=ReadCSVFile.readCSV(filepath);
		}catch(Exception e) {
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
				//responseMessage.add(Integer.parseInt(record[0])+" has been successfully inserted");
				if(dao.isEmployeeExist(employee.getEmp_id())) continue;
				dao.saveEmployee(employee);
				rowsInserted++;
				
			}catch(Exception e) {
				return 0; //error
				
			}
			
		}
		return rowsInserted;
		
						
	}
	public ArrayList<EmployeeDTO> getAllEmployees(){
		return dao.getAllEmployees();
	}
}
