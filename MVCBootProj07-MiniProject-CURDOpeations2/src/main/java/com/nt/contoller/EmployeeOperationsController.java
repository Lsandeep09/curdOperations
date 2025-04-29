package com.nt.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.Employee;
import com.nt.service.IEmployeeMgmtService;


@Controller
public class EmployeeOperationsController {
	
	@Autowired
	private IEmployeeMgmtService empService;
	
	@GetMapping("/")
	public String showHome() {
		return"home";
	}
	
	@GetMapping("/report")
	public String showEmployeeReport(Map<String, Object>map,@ModelAttribute("emp")Employee emp) {
		//use service
		Iterable <Employee> itemps = empService.getAllEmployees();
		//put the result in the model attribute
		map.put("empsList", itemps);
		// return LVN
		return "show_employee_report";
	}
	
	//register the employee
	@GetMapping("/emp_add")
	public String showFromForSaveEmployee(@ModelAttribute("emp")Employee emp) {
		return "register_employee";
	}
	
	// post method need to be to insert an emp data as register
	/*@PostMapping("/emp_add")
		public String saveEmployee(@ModelAttribute("emp")Employee emp,
															Map<String,Object>map) {
		//use the service class
		String msg=empService.registerEmployee(emp);
		Iterable<Employee>itEmps = empService.getAllEmployees();
		// keep the result in model attribute
		map.put("resultMsg", msg);
		map.put("empsList", itEmps);
		//return LVN
		return "show_employee_report";
	}*/	
	
	
		// used remove the prg pattern
	@PostMapping("/emp_add")
	public String saveEmployee(@ModelAttribute("emp") Employee emp,
	                           RedirectAttributes redirectAttrs) {
	    System.out.println("EmployeeOperationsController.saveEmployee()");
	    
	    // use the service class
	    String msg = empService.registerEmployee(emp);
	    
	    // add flash attribute
	    redirectAttrs.addFlashAttribute("resultMsg", msg);

	    // redirect to avoid double posting
	    return "redirect:emp_report";
	}

	
		@GetMapping("/emp_report")
		public String showEmployeeReport(Map<String,Object> map) {
			System.out.println("EmployeeOperationsController.showEmployeeReport()");
		    Iterable<Employee> itEmps = empService.getAllEmployees();
		    map.put("empsList", itEmps);
		    return "show_employee_report";
		}
		
		
		@GetMapping("/emp_edit")
			public String showEditEmployeeFrompage(@RequestParam("no")int no,
													@ModelAttribute("emp")Employee emp) {
			//use the service
			Employee emp1=empService.getEmployeeByno(no);
			//copy data
			BeanUtils.copyProperties(emp1, emp);
			//return the LVN
			return "update_employee";
		}
		@PostMapping("/emp_edit")
		public String editEmployee(RedirectAttributes attrs,@ModelAttribute("emp")Employee emp) {
			//use the service 
		String msg = empService.UpdateEmployee(emp);
		//add flash attributes
		attrs.addFlashAttribute("resultMsg", msg);
		// return the redirect
		return "redirect:emp_report";
	}
		
		@GetMapping("/emp_delete")
		public String deleteEmployee(RedirectAttributes attrs,
									@RequestParam int no) {
			//use the service
			String msg=empService.deleteEmployeeById(no);
			// keep the result in the flash attributes
			attrs.addFlashAttribute("resultMsg", msg);
			// redirect the request
			return "redirect:emp_report";
		}
        
		@PostMapping("/search")
		 	public String search(@ModelAttribute("emp")Employee emp,Map<String,Object>map) {
			// use the service
			List<Employee> list=empService.search(emp);
			// keep the result in shared memory as the flash attribute
			map.put("empsList", list);
			// return the LVN
			return "show_employee_report";
		}
		
}
