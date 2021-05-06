package com.cldbiz.afw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cldbiz.afw.dao.EmployeeDao;
import com.cldbiz.afw.domain.Employee;
import com.cldbiz.afw.dto.EmployeeDto;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.PageRespDto;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Resource
	private EmployeeDao employeeDao;

	@Override
	public EmployeeDto findEmployee(Long employeeId) {
		Employee employee = employeeDao.findEmployee(employeeId);
		return employee != null ? new EmployeeDto(employeeDao.findEmployee(employeeId)) : null;
	}
	
	@Override
	public List<EmployeeDto> searchEmployees(EmployeeDto employeeCriteria) {
		List<Employee> employees = employeeDao.searchEmployees(employeeCriteria);
		
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for(Employee employee: employees) {
			employeeDtos.add(EmployeeDto.asEmployeeDto(new EmployeeDto(), employee));
		}
		
		return employeeDtos;
	}
	
	@Override
	public PageRespDto<EmployeeDto> searchEmployeesPage(PageReqDto<EmployeeDto> pageReqDto) {
		PageRespDto<EmployeeDto> pageRespDto = new PageRespDto<EmployeeDto>(pageReqDto.getStart(), pageReqDto.getLimit());
		
		pageRespDto.setTotal(employeeDao.countEmployees(pageReqDto.getCriteria()));
		if (pageRespDto.getTotal() > 0) {
			List<Employee> employees = employeeDao.searchEmployeesPage(pageReqDto);
			
			List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
			for(Employee employee: employees) {
				employeeDtos.add(EmployeeDto.asEmployeeDto(new EmployeeDto(), employee));
			}
			
			pageRespDto.setResults(employeeDtos);
		}
		
		return pageRespDto;
	}

	@Override
	public void deleteEmployees(List<Long> employeeIds) {
		employeeDao.deleteEmployees(employeeIds);
	}

	@Override
	public void saveEmployee(EmployeeDto employeeDto) {
		employeeDao.saveEmployee(new Employee(employeeDto));
	}

}
