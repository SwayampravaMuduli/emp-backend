package com.employee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;
@Repository
public interface EmployeePageRepository extends PagingAndSortingRepository<Employee, Integer> {

}
