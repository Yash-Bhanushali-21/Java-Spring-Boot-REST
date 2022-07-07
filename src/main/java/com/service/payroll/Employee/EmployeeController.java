package com.service.payroll.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@GetMapping("/getEmployeeInformation/{EmployeeID}")
	String getEmp(@PathVariable Integer EmployeeID) {
		return empRepository.findById(EmployeeID).toString();
	}
	@GetMapping("/getSalary/{EmployeeID}")
	double getEmpSalary(@PathVariable Integer EmployeeID) {		
	return empRepository.findById(EmployeeID).get().getSalary();
	}
	@GetMapping("/getLeaves/{EmployeeID}")
	double getEmpLeaves(@PathVariable Integer EmployeeID) {		
	return empRepository.findById(EmployeeID).get().getLeaves();
	}
	
	
	 @PostMapping(path="/addEmployeeByParams")
	  public @ResponseBody String addNewUser (@RequestParam String name
	      , @RequestParam String email) {

	    Employee emp = new Employee();
	    emp.setName(name);
	    emp.setEmail(email);
	    empRepository.save(emp);
	    Integer newlyCreatedEmployeeID = emp.getId();
	    return String.format("New Employee with employeeID-%d Created Succesfully!", newlyCreatedEmployeeID) ;
	  }	
	 @PostMapping(path="/addEmployeesByModel")
	  public @ResponseBody String addNewUser2 (@ModelAttribute Employee emp) {
		 System.out.println(emp.getLeaves());
		 empRepository.save(emp);
	    return String.format("New Employeereceived Succesfully !");
	  }	
	 
	 
	 @PostMapping(path = "/applyLeave")
	 public @ResponseBody String applyLeaveForEmployee(@RequestParam Integer Id,@RequestParam Integer days) {
		 Employee employeeApplyingLeave = empRepository.findById(Id).get();
		 Integer alreadyTakenLeaves = employeeApplyingLeave.getLeaves();
		 if(alreadyTakenLeaves + days > 23) return "Leave Disapproved!";
		 employeeApplyingLeave.addLeaves(days);
		 return "Leave(s) applied Successfully!";
	 }
	 
	 @PostMapping(path = "/employeeResign")
	 public @ResponseBody String leavingEmployee(@RequestParam Integer Id) {
		 Employee employeeResigning = empRepository.findById(Id).get();
		 empRepository.delete(employeeResigning);
		 return "Employee Removed from DB.";
	 }
	 
	 
	 @PostMapping(path = "/setSalaryHike")
	 public @ResponseBody String applySalaryHike(@RequestParam Integer Id,@RequestParam Integer hikePercentage) {
		 Employee employeeGettingHike = empRepository.findById(Id).get();
		 double employeeCurrentSalary = employeeGettingHike.getSalary();
		 double computedHike = (double)(employeeCurrentSalary*(hikePercentage/100.0f));
		 employeeGettingHike.setSalary(employeeCurrentSalary + computedHike);
		 return "Salary Incremented";
	 }
	 
	 @GetMapping(path="/list/employees")
	  public @ResponseBody Iterable<Employee> getAllEmployees() {
	    // This returns a JSON or XML with the users
	    return empRepository.findAll();
	  }
	

}
