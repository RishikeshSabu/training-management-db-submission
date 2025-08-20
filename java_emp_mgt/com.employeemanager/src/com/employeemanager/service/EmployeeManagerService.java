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
import com.employeemanager.util.LoadErrorMessage;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeManagerService {
	private static final Logger logger = LogManager.getLogger(EmployeeManagerService.class);
	public EmployeeDao dao = new EmployeeDao();

	public int loadAndSavetoDb(String filepath) throws EmployeeServiceException {
		logger.trace("Entering loadAndSavetoDb with filepath: {}", filepath);

		int rowsInserted = 0;
		List<String[]> records = new ArrayList<>();
		try {
			records = ReadCSVFile.readCSV(filepath);
			logger.info("{} records read from csv,{}");
			
			System.out.println("Total records read: " + records.size());

		} catch (CSVFileAccessException e) {
			logger.error("Error reading CSV file from path: {}", filepath, e);
			throw new EmployeeServiceException("Emp-8", e);
			// return 0;
		}
		logger.debug("Starting to save {} employees", records.size());
		for (String[] record : records) {
			try {
				String firstNameValidator = Validation.firstNameValidator(record[1]);
				String lastNameValidator = Validation.lastNameValidator(record[2]);
				String emailValidator = Validation.emailValidator(record[3]);
				String phoneValidator = Validation.phoneValidator(record[4]);
				String departmentValidator = Validation.departmentValidator(record[5]);

				if (!firstNameValidator.equals("Valid") || !lastNameValidator.equals("Valid")
						|| !emailValidator.equals("Valid") || !phoneValidator.equals("Valid")
						|| !departmentValidator.equals("Valid")) {
					logger.warn("Skipping invalid record in CSV: emp_id={}, firstName={}, lastName={}", record[0],
							record[1], record[2]);
					continue;
				}
				EmployeeDTO employee = new EmployeeDTO(Integer.parseInt(record[0]), record[1], record[2], record[3],
						record[4], record[5], record[6], record[7]);

				if (dao.getEmployeeById(employee.getEmp_id()) != null) {
					logger.warn("Employee with employee id {} already exists, skipping insert", employee.getEmp_id());
					continue;
				}
				dao.saveEmployee(employee);
				logger.debug("Saved employee with id {} from CSV", employee.getEmp_id());
				rowsInserted++;
			} catch (EmployeeDaoException e) {
				logger.error("Failed to save employee from CSV record: emp_id={}", record[0], e);
				System.out.println(e.getMessage());
				throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
			}

		}
		logger.trace("Exiting loadAndSavetoDb, total rows inserted: {}", rowsInserted);
		
		return rowsInserted;

	}

	public List<EmployeeDTO> getAllEmployees() throws EmployeeServiceException {
		logger.trace("Entering getAllEmployees");
		try {
			List<EmployeeDTO> employees = dao.getAllEmployees();
			logger.info("Fetched {} employees", employees.size());
			logger.trace("Exiting getAllEmployees");
			return employees;
		} catch (EmployeeDaoException e) {
			logger.error(Constants.EMPLOYEE_FETCH_ERROR, e);
			throw new EmployeeServiceException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	public boolean addEmployee(EmployeeDTO employee) throws EmployeeServiceException {
		logger.trace("Entering addEmployee with emp_id: {}", employee.getEmp_id());
		try {
			String firstNameValidator = Validation.firstNameValidator(employee.getFirst_name());
			String lastNameValidator = Validation.lastNameValidator(employee.getLast_name());
			String emailValidator = Validation.emailValidator(employee.getEmail());
			String phoneValidator = Validation.phoneValidator(employee.getPhone());
			String departmentValidator = Validation.departmentValidator(employee.getDepartment());

			if (!firstNameValidator.equals("Valid") || !lastNameValidator.equals("Valid")
					|| !emailValidator.equals("Valid") || !phoneValidator.equals("Valid")
					|| !departmentValidator.equals("Valid")) {
				logger.trace("Entering addEmployee with emp_id: {}", employee.getEmp_id());
				throw new EmployeeServiceException("EMP-INVALID-1","Invalid Credentials");
			}
			EmployeeDTO isExist = dao.getEmployeeById(employee.getEmp_id());
			if (isExist != null) {
				logger.warn("Employer with id {} already exist", employee.getEmp_id());
				throw new EmployeeAlreadyExistException("EMP-12",
						"The employee id " + employee.getEmp_id() + " already exist in the table");
			}

			dao.saveEmployee(employee);
			logger.info("Successfully added employee with id: {}", employee.getEmp_id());
			logger.trace("Exiting addEmployee");
			return true;
		} catch (EmployeeDaoException e) {
			logger.error("Failed to add employee with id: {}", employee.getEmp_id(), e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		} catch (EmployeeAlreadyExistException e) {
			logger.error("Employee already exists exception for id: {}", employee.getEmp_id(), e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		}
	}

	public EmployeeDTO getEmployeebyId(int employeeId) throws EmployeeServiceException {
		logger.trace("Entering getEmployeebyId with id: {}", employeeId);
		try {
			EmployeeDTO employee = dao.getEmployeeById(employeeId);
			if (employee == null) {
				logger.warn("Employee not found with id: {}", employeeId);
				throw new EmployeeNotFoundException("EMP-3",
						"The employer with employee id " + employeeId + " doesnt exist in the table");
			}
			logger.info("Found employee with id: {}", employeeId);
			logger.trace("Exiting getEmployeebyId");
			return employee;
		} catch (EmployeeDaoException e) {
			logger.error("Error fetching employee with id: {}", employeeId, e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		} catch (EmployeeNotFoundException e) {
			logger.error("Employee not found exception for id: {}", employeeId, e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		}
	}

	public boolean updateEmployee(EmployeeDTO employee) throws EmployeeServiceException {
		logger.trace("Entering updateEmployee for emp_id: {}", employee.getEmp_id());
		try {
			boolean result = dao.updateEmployee(employee);
			if (!result) {
				logger.warn("Update failed — employee not found with id: {}", employee.getEmp_id());
				throw new EmployeeNotFoundException("EMP-3",
						"The employer with employee id " + employee.getEmp_id() + " doesnt exist in the table");
			}
			logger.info("Updated employee with id: {}", employee.getEmp_id());
			logger.trace("Exiting updateEmployee");
			return result;
		} catch (EmployeeDaoException e) {
			logger.error("Error updating employee with id: {}", employee.getEmp_id(), e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		} catch (EmployeeNotFoundException e) {
			logger.error("Employee not found exception for id: {}", employee.getEmp_id(), e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		}
	}

	public boolean deleteEmployee(int employeeId) throws EmployeeServiceException {
		logger.trace("Entering deleteEmployee with id: {}", employeeId);
		try {
			EmployeeDTO isExist = dao.getEmployeeById(employeeId);
			if (isExist == null) {
				logger.warn("The employee with id {} doesnt exist", employeeId);
				throw new EmployeeServiceException("EMP-3","Employee with id"+employeeId+"doesnt exist");
			}
			boolean isDeleted = dao.deleteEmployee(employeeId);
			logger.info("Deleted employee with id: {}", employeeId);
			logger.trace("Exiting deleteEmployee");
			return isDeleted;
		} catch (EmployeeDaoException e) {
			logger.error("Error deleting employee with id: {}", employeeId, e);
			throw new EmployeeServiceException(e.getErrorCode(),e.getMessage(), e);
		}
	}

	public int[] addEmployeesInBatch(List<EmployeeDTO> employeeList) throws EmployeeServiceException {
		logger.trace("Entering addEmployeesInBatch with {} employees", employeeList.size());
		List<EmployeeDTO> validEmployees = new ArrayList<>();
		logger.debug("Starting to add employees to validEmployee list");
		for (EmployeeDTO employee : employeeList) {
			try {
				String firstNameValidator = Validation.firstNameValidator(employee.getFirst_name());
				String lastNameValidator = Validation.lastNameValidator(employee.getLast_name());
				String emailValidator = Validation.emailValidator(employee.getEmail());
				String phoneValidator = Validation.phoneValidator(employee.getPhone());
				String departmentValidator = Validation.departmentValidator(employee.getDepartment());

				if (!firstNameValidator.equals("Valid") || !lastNameValidator.equals("Valid")
						|| !emailValidator.equals("Valid") || !phoneValidator.equals("Valid")
						|| !departmentValidator.equals("Valid")) {
					logger.warn("Validation failed for employee with id: {}", employee.getEmp_id());
					validEmployees.add(null);

					continue;
				}
				EmployeeDTO isExist = dao.getEmployeeById(employee.getEmp_id());
				if (isExist != null) {
					logger.warn("Employee with id {} already exists", employee.getEmp_id());
					validEmployees.add(null);
					continue;
				}
				validEmployees.add(employee);
			} catch (EmployeeDaoException e) {
				logger.error("Error checking existence for employee id: {}", employee.getEmp_id(), e);
				
				validEmployees.add(null);
			}
		}
		List<EmployeeDTO> insertList = new ArrayList<>();
		for (EmployeeDTO emp : validEmployees) {
			if (emp != null)
				insertList.add(emp);
		}

		int[] daoResults;
		try {
			daoResults = dao.addEmployeesInBatch(insertList);
		} catch (EmployeeDaoException e) {
			logger.error("Failed during batch insert", e);
			throw new EmployeeServiceException("EMP-10","Failed during batch insert", e);
		}
		int[] finalResults = new int[validEmployees.size()];
		int daoIndex = 0;
		for (int i = 0; i < validEmployees.size(); i++) {
			if (validEmployees.get(i) == null) {
				finalResults[i] = 0;
			} else {
				finalResults[i] = daoResults[daoIndex++];
			}
		}
		logger.info("Batch insert completed with {} employees", insertList.size());
		logger.trace("Exiting addEmployeesInBatch");
		return finalResults;
	}

	public List<Integer> transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeServiceException {
		logger.trace("Entering transferEmployeesToDepartment for {} employees to department '{}'", employeeIds.size(),newDepartment);
		try {
			List<Integer> validEmployeeIds = new ArrayList<>();
			logger.debug("Starting to add employees to validEmployeeList");
			for (int employeeId : employeeIds) {
				EmployeeDTO isExist = dao.getEmployeeById(employeeId);
				if (isExist == null) {
					logger.warn("Employee id {} does not exist; cannot transfer", employeeId);
					throw new EmployeeServiceException("EMP-3","Employee id" + employeeId + " doesnt exist");
				}
					
				
				validEmployeeIds.add(employeeId);
			}
			List<Integer> updated =dao.transferEmployeesToDepartment(validEmployeeIds, newDepartment);
			logger.info("Transferred {} employees to department '{}'", updated.size(), newDepartment);
            logger.trace("Exiting transferEmployeesToDepartment");
			return updated;
		} catch (EmployeeDaoException e) {
			logger.error("Failed to transfer employees to department '{}'", newDepartment, e);
			throw new EmployeeServiceException("EMP-11","failed to transfer departments.", e);
		}

	}

}
