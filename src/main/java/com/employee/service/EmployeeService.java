package com.employee.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeePageRepository;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository empRepository;
	@Autowired
	EmployeePageRepository repository;

	public Employee findOne(int id) {
		List<Employee> emps = empRepository.findAll();
		for (Employee emp : emps) {
			if (emp.getId() == id)
				return emp;
		}
		return null;
	}

	public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy,String fieldName) {

		Pageable paging = null;
		if (sortBy.equalsIgnoreCase("Asc")) {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(fieldName).ascending());
		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(fieldName).descending());
		}

		Page<Employee> pagedResult = repository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Employee>();
		}
	}

}
