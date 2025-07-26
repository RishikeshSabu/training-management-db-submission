package com.employeemanager.util;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;




public class ReadCSVFile {
	public static ArrayList<String[]> readCSV(String filepath){
		ArrayList<String[]> fileReader=new ArrayList<>();
		try(BufferedReader bf=new BufferedReader(new FileReader(filepath))){
			bf.readLine();
			String line="";
			while((line=bf.readLine())!=null) {
				String[] records=line.split(",");
				fileReader.add(records);
			}
			return fileReader;
		}catch(Exception e) {
			System.out.println("failed reading"+e.getMessage());
		}
		return fileReader;
	}
}
