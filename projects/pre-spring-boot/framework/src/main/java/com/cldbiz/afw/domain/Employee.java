package com.cldbiz.afw.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.cldbiz.afw.dto.EmployeeDto;

@Entity
@Table(name="EMPLOYEE", indexes = { @Index(name = "EMPLOYEE_LAST_NAME", columnList = "LAST_NAME") })
public class Employee extends EntityDomain {
	private static final long serialVersionUID = -5256303769465002652L;

	@Size(max=30)
    @Column(name="FIRST_NAME", nullable=true)
    private String firstName;
    
    @Size(max=40)
    @Column(name="LAST_NAME", nullable=true)
	private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name="HIRE_DATE", nullable=true)
    private Date hireDate;

    @Column(name="SALARY", nullable=true)
    private Double salary;

    // relationships
    
    public Employee() {}
    
    public Employee(EmployeeDto employeeDto) {
    	Employee.asEmployee(this,  employeeDto);
    }
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getHireDate() {
		return hireDate;
	}
	
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public static Employee asEmployee(Employee employee, EmployeeDto employeeDto) {
		if (employee == null) {
			employee = new Employee();
		}

		if (employeeDto == null) {
			employee = null;
		}
		else {
	    	employee.setId(employeeDto.getId());
	    	employee.setFirstName(employeeDto.getFirstName());
	    	employee.setLastName(employeeDto.getLastName());
	    	employee.setSalary(employeeDto.getSalary());
	    	employee.setHireDate(employeeDto.getHireDate());
		}
		
		return employee;
	}
}
