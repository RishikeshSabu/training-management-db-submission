package com.employeemanager.service;

import com.employeemanager.dto.Response;
import com.employeemanager.util.ReadCSVFile;
import com.employeemanager.dao.EmployeeDao;
import com.employeemanager.dto.EmployeeDTO;

import java.util.ArrayList;

public class EmployeeManagerService {
	public static Response<ArrayList<String>> loadAndSavetoDb(String filepath){
		ArrayList<String> responseMessage=new ArrayList<>();
		if(!filepath.toLowerCase().endsWith(".csv")) return Response.failure("The file must be a csv file");
		ArrayList<String[]> records=new ArrayList<>();
		try {
			records=ReadCSVFile.readCSV(filepath);
		}catch(Exception e) {
			return Response.failure("Error in fetching file | reason : "+e.getMessage());
		}
		for (String[] record : records) {
			try {
				EmployeeDTO emp=new EmployeeDTO(Integer.parseInt(record[0]),record[1],record[2],record[3],record[4],record[5],record[6],record[7]);
				//responseMessage.add(Integer.parseInt(record[0])+" has been successfully inserted");
				Response<Integer> response=EmployeeDao.insertIntoDB(emp);
				if (response.isSuccess()) {
		            responseMessage.add(record[0] + " has been successfully inserted");
		        } else {
		            responseMessage.add(record[0] + " failed to insert: " + response.getErrorMessage());
		        }
			}catch(Exception e) {
				responseMessage.add("There has been an error in inserting the employer detail with epm id "+Integer.parseInt(record[0]));
			}
			
		}
		return Response.success(responseMessage);
						
	}
}
