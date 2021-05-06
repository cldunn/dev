package com.cldbiz.afw.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.cldbiz.afw.domain.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EmployeeDto extends BaseDto {
	private static final long serialVersionUID = -2890156966159058757L;

	@Size(max=40)
	private String firstName;
	
	@Size(max=40)
	private String lastName;
	
	@Digits(integer=18, fraction=2)
	private Double salary;
	
	private Date hireDate;
	
	// calculated and transient fields
	private String name;
	
	// initialize collections
	
	public EmployeeDto() {}
	
	public EmployeeDto(Employee employee) {
		EmployeeDto.asEmployeeDto(this, employee);
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
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Date getHireDate() {
		return hireDate;
	}
	
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static EmployeeDto asEmployeeDto(EmployeeDto employeeDto, Employee employee) {
		if (employeeDto == null) {
			employeeDto = new EmployeeDto();
		}

		if (employee == null) {
			employeeDto = null;
		}
		else {
			employeeDto.setId(employee.getId());
			employeeDto.setFirstName(employee.getFirstName());
			employeeDto.setLastName(employee.getLastName());
			employeeDto.setSalary(employee.getSalary());
			employeeDto.setHireDate(employee.getHireDate());
		}

		return employeeDto;
	}
	
	/*
	public Employee updateEmployee(Employee employee) {
		employee.setFirstName(this.getFirstName());
		employee.setLastName(this.getLastName());
		employee.setSalary(this.getSalary());
		employee.setHireDate(this.getHireDate());
		
		return employee;
	}

	public static List<EmployeeDto> asEmployeeDtos(List<Employee> employees) {
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for(Employee employee: employees) {
			employeeDtos.add(new EmployeeDto(employee));
		}
		
		return employeeDtos;
	}
	*/
}
