package com.cldbiz.afw.service;

import java.util.List;

import com.cldbiz.afw.dto.EmployeeDto;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.PageRespDto;

public interface EmployeeService {
	public EmployeeDto findEmployee(Long employeeId);
	public List<EmployeeDto> searchEmployees(EmployeeDto employeeCriteria);
	public PageRespDto<EmployeeDto> searchEmployeesPage(PageReqDto<EmployeeDto> pageReqDto);
	public void deleteEmployees(List<Long> employeeIds);
	public void saveEmployee(EmployeeDto employeeDto);
}
