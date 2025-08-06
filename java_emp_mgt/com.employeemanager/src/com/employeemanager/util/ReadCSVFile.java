package com.employeemanager.util;

import com.employeemanager.exceptions.CSVFileAccessException;
import com.employeemanager.constant.Constants;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;




public class ReadCSVFile {
	public static List<String[]> readCSV(String filepath) throws CSVFileAccessException{
		List<String[]> fileReader=new ArrayList<>();
		try(BufferedReader bf=new BufferedReader(new FileReader(filepath))){
			bf.readLine();
			String line="";
			while((line=bf.readLine())!=null) {
				String[] records=line.split(",");
				fileReader.add(records);
			}
			return fileReader;
		}catch(Exception e) {
			throw new CSVFileAccessException(Constants.CSV_ERROR);
			//System.out.println("failed reading"+e.getMessage());
		}
		//return fileReader;
	}
}
