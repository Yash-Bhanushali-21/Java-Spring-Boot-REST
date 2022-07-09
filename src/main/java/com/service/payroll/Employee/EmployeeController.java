package com.service.payroll.Employee;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.service.payroll.Employee.Employee;
import com.service.payroll.exception.InvalidHikePercentageException;
@RequestMapping("/api")
@RestController
public class EmployeeController {

	@Autowired //get the bean created and inject directly in here.
	private EmployeeRepository empRepository;
	
	@GetMapping("/getEmployeeInformation/{EmployeeID}")
	public @ResponseBody Employee getEmp(@PathVariable Integer EmployeeID)  {
		//sending the found entry in a json form.
		Employee emp = empRepository.findById(EmployeeID).get();
		System.out.println(emp);

		return emp;
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
		 empRepository.save(emp);
	    return String.format("New Employeereceived Succesfully !");
	  }	
	 
	 
	 @PostMapping(path = "/applyLeave")
	 public @ResponseBody String applyLeaveForEmployee(@RequestParam Integer Id,@RequestParam Integer days) {
		 Employee employeeApplyingLeave = empRepository.findById(Id).get();
		 Integer alreadyTakenLeaves = employeeApplyingLeave.getLeaves();
		 if(alreadyTakenLeaves + days > 23) return "Leave Disapproved!";
		 employeeApplyingLeave.setLeaves(days);
		 return "Leave(s) applied Successfully!";
	 }
	 
	 @PostMapping(path = "/employeeResign")
	 public @ResponseBody String leavingEmployee(@RequestParam Integer Id) {
		 Employee employeeResigning = empRepository.findById(Id).get();
		 empRepository.delete(employeeResigning);
		 return "Employee Removed from DB.";
	 }
	 
	 
	 @PutMapping(path = "/setSalaryHike")
	 public @ResponseBody String applySalaryHike(@RequestParam Integer Id,@RequestParam Integer hikePercentage) throws InvalidHikePercentageException {
		 Employee employeeGettingHike = empRepository.findById(Id).get();

		 if(hikePercentage<10 || hikePercentage > 50) throw new InvalidHikePercentageException("Hike Should be between 10 to 50%!");
		 
		 try {
			 double employeeCurrentSalary = employeeGettingHike.getSalary();
			 double computedHike = (double)(employeeCurrentSalary*(hikePercentage/100.0f));
			 employeeGettingHike.setSalary(employeeCurrentSalary + computedHike);
			 
		 }
		 catch (Exception e) {
			 System.out.println("Something went wrong" + e.getMessage()); 
		 }
		 
		 return "Salary Incremented";
	 }
	 
	 @GetMapping(path="/list/employees")
	  public @ResponseBody Iterable<Employee> getAllEmployees() {
	    // This returns a JSON or XML with the users
	    return empRepository.findAll();
	  }
	
	


}
