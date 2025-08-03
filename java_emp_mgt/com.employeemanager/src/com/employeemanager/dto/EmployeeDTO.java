package com.employeemanager.dto;

public class EmployeeDTO {
	private int emp_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String department;
    private String salary;
    private String join_date;
	
	public EmployeeDTO(int emp_id,String first_name, String last_name, String email, String phone, String department, String salary, String join_date) {
		this.emp_id=emp_id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.phone=phone;
		this.department=department;
		this.salary=salary;
		this.join_date=join_date;
	}
	
	public int getEmp_id() {
        return emp_id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getSalary() {
        return salary;
    }
    public String getJoin_date() {
        return join_date;
    }
	public String toString() {
		return first_name+" "+last_name+" - phone : "+phone;
	}
}
