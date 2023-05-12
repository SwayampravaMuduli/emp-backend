package com.employee.dto;

public class EmployeeDto {
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
	public void setAge(String age) {
		this.age = age;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	

}
