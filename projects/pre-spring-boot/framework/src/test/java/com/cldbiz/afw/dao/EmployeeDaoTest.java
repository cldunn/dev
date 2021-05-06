package com.cldbiz.afw.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.domain.Employee;
import com.cldbiz.afw.dto.EmployeeDto;
import com.cldbiz.afw.dto.PageReqDto;

public class EmployeeDaoTest extends AfwBaseTest {

	private static Timestamp now;
	private static Logger logger = Logger.getLogger(EmployeeDaoTest.class);
	
	@Resource
	private EmployeeDao employeeDao;
	
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
	public void testGetEmployee() {
		logger.debug("THIS IS TEST");
		
		Employee employee = employeeDao.findEmployee(1L);
		
		assertNotNull(employee);
	}


	@Test
	public void testIsDuplicate() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeDto employeeDto = new EmployeeDto();

		employeeDto.setLastName("Dunn");
		try {
			employeeDto.setHireDate(fmt.parse("2016-04-14"));
		}
		catch (ParseException pe) {
			fail();
		}
		boolean isDup = employeeDao.isDuplicateEmployee(employeeDto);
		
		assertTrue(isDup);
		
		employeeDto.setLastName("Dunn");
		try {
			employeeDto.setHireDate(fmt.parse("2015-04-14"));
		}
		catch (ParseException pe) {
			fail();
		}
		isDup = employeeDao.isDuplicateEmployee(employeeDto);
		
		assertFalse(isDup);
	}
	
	@Test
	public void testCountEmployees() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeDto employeeCriteria = new EmployeeDto();

		employeeCriteria.setLastName("Dunn");
		try {
			employeeCriteria.setHireDate(fmt.parse("2016-04-14"));
		}
		catch (ParseException pe) {
			fail();
		}
		Long empCnt = employeeDao.countEmployees(employeeCriteria);
		
		assertTrue(empCnt > 0);
	}
	
	@Test
	public void testFindEmployees() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeDto employeeCriteria = new EmployeeDto();

		employeeCriteria.setLastName("Dunn");
		try {
			employeeCriteria.setHireDate(fmt.parse("2016-04-14"));
		}
		catch (ParseException pe) {
			fail();
		}
		List<Employee> employees = employeeDao.findEmployees(employeeCriteria);
		
		assertTrue(employees.size() == 1);

		employeeCriteria.setFirstName("Du");
		employees = employeeDao.findEmployees(employeeCriteria);
		
		assertTrue(employees.size() == 0);
	}

	@Test
	public void testSearchEmployees() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeDto employeeCriteria = new EmployeeDto();

		employeeCriteria.setLastName("Dunn");
		try {
			employeeCriteria.setHireDate(fmt.parse("2016-04-14"));
		}
		catch (ParseException pe) {
			fail();
		}
		List<Employee> employees = employeeDao.searchEmployees(employeeCriteria);
		
		assertTrue(employees.size() == 1);

		employeeCriteria.setLastName("Du");
		employees = employeeDao.searchEmployees(employeeCriteria);
		
		assertTrue(employees.size() >= 1);
	}

	@Test
	public void testSearchEmployeesPage() {
		PageReqDto<EmployeeDto> pageReqDto = new PageReqDto<EmployeeDto>(0L, 1L);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeDto employeeCriteria = new EmployeeDto();

		employeeCriteria.setLastName("Dunn");
		pageReqDto.setCriteria(employeeCriteria);
		List<Employee> employees = employeeDao.searchEmployeesPage(pageReqDto);
		
		assertTrue(employees.size() == 1);
		
	}
	
	@Test
	public void testDeleteEmployees() {
		EmployeeDto employeeCriteria = new EmployeeDto();
		employeeCriteria.setLastName("Dunn");
		
		List<Employee> employees = employeeDao.findEmployees(employeeCriteria);
		assert(employees.size() > 0);
		
		List<Long> employeeIds = new ArrayList<Long>();
		employeeIds.add(employees.get(0).getId());
		employeeDao.deleteEmployees(employeeIds);
		
		Long cnt = employeeDao.countEmployees(employeeCriteria);
		assert((cnt + 1) == employees.size());
	}
	
	@Test
	public void testSaveEmployee() {
		EmployeeDto employeeCriteria = new EmployeeDto();
		employeeCriteria.setLastName("Dunn");
		
		Long original = employeeDao.countEmployees(employeeCriteria);
		
		Employee employee = new Employee();
		employee.setFirstName("Sean");
		employee.setLastName("Dunn");
		employee.setHireDate(new Date());
		employee.setSalary(345678.90);
		
		employeeDao.saveEmployee(employee);
		
		Long count = employeeDao.countEmployees(employeeCriteria);
		assert((original + 1) == count);
	}
	
	@Test
	public void testSaveEmployees() {
		EmployeeDto employeeCriteria = new EmployeeDto();
		Long original = employeeDao.countEmployees(employeeCriteria);

		Employee employee1 = new Employee();
		employee1.setFirstName("Sean");
		employee1.setLastName("Dunn");
		employee1.setHireDate(new Date());
		employee1.setSalary(345678.90);
		
		Employee employee2 = new Employee();
		employee2.setFirstName("John");
		employee2.setLastName("Smith");
		employee2.setHireDate(new Date());
		employee2.setSalary(4567890.12);
		
		List<Employee> employeeDtos = new ArrayList<Employee>();
		employeeDtos.add(employee1);
		employeeDtos.add(employee2);
		
		employeeDao.saveEmployees(employeeDtos);
		
		Long count = employeeDao.countEmployees(employeeCriteria);
		assertTrue((original + 2) == count);
	}
}
