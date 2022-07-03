package com.service.payroll.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Employee {
  @Id // primary key.
  @GeneratedValue(strategy=GenerationType.AUTO) //auto increment.
  @Column(nullable=false)
  private Integer id;
  
  @Column(name="employee_id")
  private Integer employeeID;
  
  @Column(name="employee_name")
  private String name;

  @Column(name="employee_email")
  private String email;

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
}