package com.nt.service;

import java.util.List;

import com.nt.model.Employee;

public interface IEmployeeMgmtService {

	public Iterable<Employee> getAllEmployees();
	
	// to add an employee in the mvc  which is known as an insert operation
	public String registerEmployee(Employee emp);
	public Employee getEmployeeByno(int eno);
	public String UpdateEmployee(Employee emp);
	public String deleteEmployeeById(int eno);
	public List<Employee> search(Employee emp);
}
