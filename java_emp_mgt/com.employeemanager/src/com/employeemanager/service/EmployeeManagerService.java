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
import com.employeemanager.exceptions.EmployeeAlreadyExistException;

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
				
				if(dao.getEmployeeById(employee.getEmp_id())!=null) continue;
				dao.saveEmployee(employee);
				rowsInserted++;
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
	public boolean addEmployee(EmployeeDTO employee) throws EmployeeServiceException {
		try {
			String firstNameValidator = Validation.firstNameValidator(employee.getFirst_name());
			String lastNameValidator = Validation.lastNameValidator(employee.getLast_name());
			String emailValidator = Validation.emailValidator(employee.getEmail());
			String phoneValidator = Validation.phoneValidator(employee.getPhone());
			String departmentValidator = Validation.departmentValidator(employee.getDepartment());

			if (!firstNameValidator.equals("Valid") ||
			        !lastNameValidator.equals("Valid") ||
			        !emailValidator.equals("Valid") ||
			        !phoneValidator.equals("Valid") ||
			        !departmentValidator.equals("Valid")) {
			    throw new EmployeeServiceException("Invalid Credentials");
			}
		EmployeeDTO isExist=dao.getEmployeeById(employee.getEmp_id());
		if(isExist!=null) {
			throw new EmployeeAlreadyExistException("The employee id "+employee.getEmp_id()+" already exist in the table");
		}
		
		dao.saveEmployee(employee);
		return true;
		}catch(EmployeeDaoException e) {
			throw new EmployeeServiceException(Constants.INSERTION_FAILED,e);
		}catch(EmployeeAlreadyExistException e) {
			throw new EmployeeServiceException(e.getMessage(),e);
		}
	}
	
	public EmployeeDTO getEmployeebyId(int employeeId) throws EmployeeServiceException{
		try {
			EmployeeDTO employee=dao.getEmployeeById(employeeId);
			if(employee==null) {
				throw new EmployeeNotFoundException("The employer with employee id "+employeeId+" doesnt exist in the table");
			}
			return employee;
		}catch(EmployeeDaoException e) {
			throw new EmployeeServiceException(e.getMessage(),e);
		}catch(EmployeeNotFoundException e) {
			throw new EmployeeServiceException(e.getMessage(),e);
		}
	}
	public boolean updateEmployee(EmployeeDTO employee) throws EmployeeServiceException{
		try {
			boolean result=dao.updateEmployee(employee);
			if(!result) throw new EmployeeNotFoundException("The employer with employee id "+employee.getEmp_id()+" doesnt exist in the table");
			return result;
		}catch(EmployeeDaoException e) {
			throw new EmployeeServiceException(e.getMessage(),e);
		}catch(EmployeeNotFoundException e) {
			throw new EmployeeServiceException(e.getMessage(),e);
		}
	}
	public boolean deleteEmployee(int employeeId) throws EmployeeServiceException{
		try {
			EmployeeDTO isExist=dao.getEmployeeById(employeeId);
			if(isExist==null) throw new EmployeeServiceException(Constants.NO_EMPLOYEE_ERROR);
	        return dao.deleteEmployee(employeeId);
	    } catch (EmployeeDaoException e) {
	        throw new EmployeeServiceException(e.getMessage(), e);
	    }
	}
}
