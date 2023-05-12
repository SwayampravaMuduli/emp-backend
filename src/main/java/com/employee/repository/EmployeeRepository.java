package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.employee.entity.Employee;

import jakarta.transaction.Transactional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value="Select name from employee_tbl where name=:name",nativeQuery=true)
	List<String> findName(String name);
    
    @Query(value="Select id from employee_tbl where id=:id",nativeQuery=true)
	List<Integer> findId(int id);
    @Transactional
    @Modifying
    @Query(value="update employee_tbl set name=:name,age=:age,salary=:salary where id=:id",nativeQuery=true)
	Integer update(int id,String name,String age,String salary);

	void save(Integer employee2);


	

}
