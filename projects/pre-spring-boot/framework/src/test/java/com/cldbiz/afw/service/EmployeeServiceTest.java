package com.cldbiz.afw.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.domain.Employee;
import com.cldbiz.afw.dto.EmployeeDto;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.PageRespDto;

public class EmployeeServiceTest extends AfwBaseTest {
	private static Timestamp now;
	
	@Resource
	private EmployeeService employeeService;
	
	@BeforeClass
	public static void setUpBeforeTests() {
		refresh("com/cldbiz/afw/data/uniqueid.xml");
		refresh("com/cldbiz/afw/data/employee.xml");
		now = new Timestamp(new Date().getTime());
		
	}

	@AfterClass
	public static void cleanAfterTests() {
		delete("com/cldbiz/afw/data/employee.xml");
		delete("com/cldbiz/afw/data/uniqueid.xml");
	}
	
	@Test
	public void testFindEmployee() {
		EmployeeDto employeeDto = employeeService.findEmployee(1L);
		assertNotNull(employeeDto);
		
		employeeDto = employeeService.findEmployee(-1L);
		assertNull(employeeDto);
	}

	@Test
	public void testSearchEmployees() {
		EmployeeDto employeeCriteria = new EmployeeDto();
		List<EmployeeDto> employeeDtos = employeeService.searchEmployees(employeeCriteria);
		assertTrue(employeeDtos.size() > 0);
	}
	
	@Test
	public void testSearchEmployeesPage() {
		PageReqDto<EmployeeDto> pageReqDto = new PageReqDto<EmployeeDto>(0L, 1L);
		pageReqDto.setCriteria(new EmployeeDto());
		
		PageRespDto<EmployeeDto> pageRespDto = employeeService.searchEmployeesPage(pageReqDto);
		assertTrue(pageRespDto.getResults().size() == 1);
		
		List<EmployeeDto> employeeDtos = employeeService.searchEmployees(pageReqDto.getCriteria());
		assert(pageRespDto.getTotal() == employeeDtos.size());
	}

	@Test
	public void testDeleteEmployees() {
		EmployeeDto employeeCriteria = new EmployeeDto();
		List<EmployeeDto> employeeDtos = employeeService.searchEmployees(employeeCriteria);
		int original = employeeDtos.size();
		assert(original > 0);
		
		List<Long> employeeIds = new ArrayList<Long>();
		employeeIds.add(employeeDtos.get(0).getId());
		
		employeeService.deleteEmployees(employeeIds);
		
		employeeDtos = employeeService.searchEmployees(employeeCriteria);
		int count = employeeDtos.size();
		assert((count + 1) == original);
	}

	@Test
	public void testSaveEmployee() {
		EmployeeDto employeeCriteria = new EmployeeDto();
		List<EmployeeDto> employeeDtos = employeeService.searchEmployees(employeeCriteria);
		int original = employeeDtos.size();
		assert(original > 0);
		
		Employee employee = new Employee();
		employee.setFirstName("Sean");
		employee.setLastName("Dunn");
		employee.setHireDate(new Date());
		employee.setSalary(345678.90);
		
		employeeService.saveEmployee(new EmployeeDto(employee));
		
		employeeDtos = employeeService.searchEmployees(employeeCriteria);
		int count = employeeDtos.size();
		assert((original + 1) == count);
	}
}
