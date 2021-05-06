package com.cldbiz.afw.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.Employee;
import com.cldbiz.afw.domain.QEmployee;
import com.cldbiz.afw.dto.EmployeeDto;
import com.cldbiz.afw.dto.PageReqDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public Employee findEmployee(Long employeeId) {
		QEmployee qEmployee = QEmployee.employee;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qEmployee.id.eq(employeeId));
		
		return jpaQueryFactory.selectFrom(qEmployee)
				.where(builder)
				.fetchOne();
	}

	@Override
	public Boolean isDuplicateEmployee(EmployeeDto employeeDto) {
		QEmployee employee = QEmployee.employee;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (employeeDto.getId() != null && employeeDto.getId() > 0) {
			builder.and(employee.id.ne(employeeDto.getId()));
		}
		builder.and(employee.lastName.eq(employeeDto.getLastName()));
		builder.and(employee.hireDate.eq(employeeDto.getHireDate()));
		
		return jpaQueryFactory.from(employee)
				.where(builder)
				.fetchCount() > 0 ? true : false;
	}

	@Override
	public Long countEmployees(EmployeeDto employeeCriteria) {
		QEmployee qEmployee = QEmployee.employee;
		
		return jpaQueryFactory.from(qEmployee)
				.where(findPredicate(employeeCriteria))
				.fetchCount();
	}

	@Override
	public List<Employee> findEmployees(EmployeeDto employeeCriteria) {
		QEmployee qEmployee = QEmployee.employee;
		
		return jpaQueryFactory.selectFrom(qEmployee)
				.where(findPredicate(employeeCriteria))
				.orderBy(sortByEmployeeName())
				.fetch();
	}

	@Override
	public List<Employee> searchEmployees(EmployeeDto employeeCriteria) {
		QEmployee qEmployee = QEmployee.employee;
		
		return jpaQueryFactory.selectFrom(qEmployee)
				.where(searchPredicate(employeeCriteria))
				.orderBy(sortByEmployeeName())
				.fetch();
	}

	@Override
	public List<Employee> searchEmployeesPage(PageReqDto<EmployeeDto> pageReqDto) {
		QEmployee qEmployee = QEmployee.employee;
		
		return jpaQueryFactory.selectFrom(qEmployee)
				.where(searchPredicate(pageReqDto.getCriteria()))
				.orderBy(sortByEmployeeName())
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public void deleteEmployees(List<Long> employeeIds) {
		QEmployee employee = QEmployee.employee;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(employee.id.in(employeeIds));
		
		jpaQueryFactory.delete(employee)
			.where(builder).execute();
	}

	@Override
	public void removeEmployees(List<Long> employeeIds) {
		Integer blockSz = 50;
		Integer fromNdx = 0;
		Integer toNdx = 0;
		
		QEmployee qEmployee = QEmployee.employee;

		while (fromNdx < employeeIds.size()) {
			toNdx = Math.min(fromNdx + blockSz, employeeIds.size());

			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qEmployee.id.in(employeeIds.subList(fromNdx, toNdx)));
			remove(jpaQueryFactory.selectFrom(qEmployee).where(builder).fetch());
			
			fromNdx = Math.min(toNdx, employeeIds.size());
		}
	}

	public Employee saveEmployee(Employee employee) {
		return save(employee);
	}
	
	public List<Employee> saveEmployees(List<Employee> employees) {
		return save(employees);
	}
	
	private Predicate findPredicate(EmployeeDto employeeDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QEmployee employee = QEmployee.employee;
		
		if (StringUtils.isNotBlank(employeeDto.getFirstName())) {
			builder.and((Predicate) employee.firstName.equalsIgnoreCase(employeeDto.getFirstName()));
		}

		if (StringUtils.isNotBlank(employeeDto.getLastName())) {
			builder.and((Predicate) employee.lastName.equalsIgnoreCase(employeeDto.getLastName()));
		}
		
		if (employeeDto.getHireDate() != null) {
			builder.and((Predicate) employee.hireDate.eq(employeeDto.getHireDate()));
		}
		
		if (employeeDto.getSalary() != null) {
			builder.and((Predicate) employee.salary.eq(employeeDto.getSalary()));
		}
		
		return builder;
	}

	private Predicate searchPredicate(EmployeeDto employeeDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QEmployee employee = QEmployee.employee;
		
		if (StringUtils.isNotBlank(employeeDto.getFirstName())) {
			builder.and((Predicate) employee.firstName.containsIgnoreCase(employeeDto.getFirstName()));
		}

		if (StringUtils.isNotBlank(employeeDto.getLastName())) {
			builder.and((Predicate) employee.lastName.containsIgnoreCase(employeeDto.getLastName()));
		}
		
		if (employeeDto.getHireDate() != null) {
			builder.and((Predicate) employee.hireDate.eq(employeeDto.getHireDate()));
		}
		
		if (employeeDto.getSalary() != null) {
			builder.and((Predicate) employee.salary.eq(employeeDto.getSalary()));
		}
		
		return builder;
	}
	
	private OrderSpecifier[] sortByEmployeeName() {
		return new OrderSpecifier[] {
			QEmployee.employee.lastName.asc(),
			QEmployee.employee.firstName.asc()
		};
	}
}
