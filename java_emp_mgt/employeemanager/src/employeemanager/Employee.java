package employeemanager;

public class Employee {
	int emp_id;
	String first_name;
	String last_name;
	String email;
	String phone;
	String department;
	String salary;
	String join_date;
	
	public Employee(int emp_id,String first_name, String last_name, String email, String phone, String department, String salary, String join_date) {
		this.emp_id=emp_id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.phone=phone;
		this.department=department;
		this.salary=salary;
		this.join_date=join_date;
	}
//	public String toString() {
//		return first_name+" "+last_name;
//	}
}
