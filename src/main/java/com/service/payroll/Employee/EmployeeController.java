package com.service.payroll.Employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@GetMapping("/getEmployee/{EmployeeID}")
	String getEmp(@PathVariable Long EmployeeID) {
		return "Employee required with ${EmployeeID}";
	}
	

}
