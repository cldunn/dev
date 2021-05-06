package com.cldbiz.afw.dao;

import java.util.List;

import com.cldbiz.afw.domain.Employee;
import com.cldbiz.afw.dto.EmployeeDto;
import com.cldbiz.afw.dto.PageReqDto;

public interface EmployeeDao extends BaseDao<Employee> {
	public Employee findEmployee(Long employeeId);
	public Boolean isDuplicateEmployee(EmployeeDto employeeDto);
	
	public Long countEmployees(EmployeeDto employeeCriteria);
	public List<Employee> findEmployees(EmployeeDto employeeCriteria);
	public List<Employee> searchEmployees(EmployeeDto employeeCriteria);
	public List<Employee> searchEmployeesPage(PageReqDto<EmployeeDto> pgReqDto);

	public void deleteEmployees(List<Long> employeeIds);
	public void removeEmployees(List<Long> employeeIds);
	public Employee saveEmployee(Employee employee);
	public List<Employee> saveEmployees(List<Employee> employees);
}
