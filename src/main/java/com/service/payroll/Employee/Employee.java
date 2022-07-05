package com.service.payroll.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Employee {
  @Id // primary key.
  @GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment.
  @Column( name = "employee_id",nullable=false)
  private Integer id;
  
  @Column(name="employee_name")
  private String name;

  @Column(name="employee_email")
  private String email;

  @Column(name="employee_salary")
  private double salary;
  
  @Column(name="employee_leaves")
  private Integer leaves;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public double getSalary() {
	    return salary;
  }
   public void setSalary(double salary) {
	    this.salary = salary;
	}
   public Integer getLeaves() {
	    return leaves;
	  }

	  public void addLeaves(Integer leaves) {
	    this.leaves = leaves;
	  }
}