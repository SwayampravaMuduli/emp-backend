package com.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="employee_tbl")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String age;
	private String salary;
	public Integer getId() {
		return id;
	}
	public Integer setId(Integer id) {
		return this.id = id;
	}
	public String getName() {
		return name;
	}
	public String setName(String name) {
		return this.name = name;
	}
	public String getAge() {
		return age;
	}
	public String setAge(String age) {
		return this.age = age;
	}
	public String getSalary() {
		return salary;
	}
	public String setSalary(String salary) {
		return this.salary = salary;
	}
	

}
