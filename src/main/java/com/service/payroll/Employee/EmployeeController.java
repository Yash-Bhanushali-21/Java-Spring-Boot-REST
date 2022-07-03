package com.service.payroll.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.service.payroll.Employee.Employee;
@RestController
public class EmployeeController {

	@Autowired //get the bean created and inject directly in here.
	private EmployeeRepository empRepository;
	
	@GetMapping("/getEmployee/{EmployeeID}")
	String getEmp(@PathVariable Integer EmployeeID) {
		return empRepository.findById(EmployeeID).toString();
	}
	
	 @PostMapping(path="/addEmployee") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam String name
	      , @RequestParam String email) {

	    Employee emp = new Employee();
	    emp.setName(name);
	    emp.setEmail(email);
	    empRepository.save(emp);
	    Integer newlyCreatedEmployeeID = emp.getId();
	    return String.format("New Employee with employeeID-%d Created Succesfully!", newlyCreatedEmployeeID) ;
	  }	
	 
	 @GetMapping(path="/list/employees")
	  public @ResponseBody Iterable<Employee> getAllEmployees() {
	    // This returns a JSON or XML with the users
	    return empRepository.findAll();
	  }
	

}
