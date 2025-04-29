package com.nt.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.model.Employee;
import com.nt.repo.IEmployeeRepository;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {

	@Autowired
	private IEmployeeRepository empRepo;
	@Override
	public Iterable<Employee> getAllEmployees() {
		List<Employee>list = StreamSupport.stream(empRepo.findAll().spliterator(),false).toList();
		
		//return list.stream().sorted().toList();//performs natural sorting
		return list.stream().sorted((emp1,emp2)->emp1.getEname().compareTo(emp2.getEname())).toList();
	}
	@Override
	public String registerEmployee(Employee emp) {
		return "employee is saved with id value:"+empRepo.save(emp).getEmpno();
	}
	@Override
	public Employee getEmployeeByno(int eno) {
		Employee emp = empRepo.findById(eno).orElseThrow(()->new IllegalArgumentException("invalid id"));
		return emp;
	}
	@Override
	public String UpdateEmployee(Employee emp) {
		
		return"Employee is Updated with having id value::"+empRepo.save(emp).getEmpno();
	}
	@Override
	public String deleteEmployeeById(int eno) {
		empRepo.deleteById(eno);
		return eno+" Eemployee id is deleted";
	}
	@Override
	public List<Employee> search(Employee emp) {
	    // Clean input: convert blank fields to null
	    if (emp.getEname() == null || emp.getEname().trim().isEmpty())
	        emp.setEname(null);

	    if (emp.getJob() == null || emp.getJob().trim().isEmpty())
	        emp.setJob(null);

	  //  if (emp.getSal() != null && emp.getSal() <= 0)
	   //     emp.setSal(null); // or set rule accordingly

	    if (emp.getDeptno() != null && emp.getDeptno() <= 0)
	        emp.setDeptno(null); // optional check

	    // If all fields are null → return all employees
	    if (emp.getEname() == null && emp.getJob() == null &&
	         emp.getDeptno() == null) {
	        return empRepo.findAll();
	    }

	    // Create Example query
	    Example<Employee> example = Example.of(emp);
	    return empRepo.findAll(example);
	}


}