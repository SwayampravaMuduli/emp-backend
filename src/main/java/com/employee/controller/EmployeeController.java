package com.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.dto.ResponseEntityCode;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository empRepository;
	@Autowired
	EmployeeService service;
    @CrossOrigin("*")
	@PostMapping("/addEmployee")
	public ResponseEntity<Object> addEmployee(@RequestBody EmployeeDto employee) {
		if (employee == null) {

			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("body should not have empty"),
					HttpStatus.BAD_REQUEST);
		}
		if (employee.getName() == null || employee.getName() == "") {

			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("employee must be have name"),
					HttpStatus.BAD_REQUEST);

		}
		if (employee.getSalary() == null || employee.getSalary() == "") {

			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("employee must be have salary"),
					HttpStatus.BAD_REQUEST);

		}
		if (employee.getAge() == null || employee.getAge() == "") {

			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("employee must be have age"),
					HttpStatus.BAD_REQUEST);

		}
		List<String> name = empRepository.findName(employee.getName());
		Employee employees = new Employee();
		System.out.println(name);
		String nameOne = null;

		for (String nameOneOne : name) {

			nameOne = employees.setName(employee.setName(nameOne));

		}

		if (employee.getName() == nameOne) {
			System.out.println(nameOne);
			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("employee name already exists"),
					HttpStatus.BAD_REQUEST);

		}
		employees.setName(employee.getName());
		employees.setSalary(employee.getSalary());
		employees.setAge(employee.getAge());
		employees = empRepository.save(employees);

		return new ResponseEntity<Object>(
				ResponseEntityCode.getReturnObjectString("You sucessfully added employee details"), HttpStatus.OK);

	}
    @CrossOrigin("*")
	@GetMapping("/getEmployee")
	public ResponseEntity<Object> getEmployee() {
		List<Employee> employee = new ArrayList<>();
		employee = empRepository.findAll();
		if (employee == null) {
			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("employee is not found "),
					HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<Object>(ResponseEntityCode.getReturnObject(employee), HttpStatus.OK);

	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Object> retriveEmployee(@PathVariable int id) throws Exception {
		Employee employee = service.findOne(id);
		if (employee == null) {

			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("id: " + id + " is not present"),
					HttpStatus.OK);
		}

		return new ResponseEntity<Object>(ResponseEntityCode.getReturnObject(employee), HttpStatus.OK);
	}

	@DeleteMapping("/employeeDetails/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int id) throws Exception {

		List<Integer> employeeDetails = empRepository.findId(id);
		Integer id2 = null;
		for (Integer employeeDetailsOne : employeeDetails) {
			id2 = employeeDetailsOne;
		}
		if (id2 != id) {
			return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString(id + "id  not present"),
					HttpStatus.OK);

		}

		empRepository.deleteById(id2);

		return new ResponseEntity<Object>(
				ResponseEntityCode.getReturnObjectString("You have successfully deleted by id" + id), HttpStatus.OK);

	}

	@PatchMapping("/employees/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable(value = "id") int id, @RequestBody EmployeeDto employee)
			throws Exception {

		Employee employees = new Employee();

		employees.setName(employee.getName());
		employees.setSalary(employee.getSalary());
		employees.setAge(employee.getAge());
		employees = empRepository.save(employees);

		Integer employee2 = empRepository.update(id, employees.getName(), employees.getAge(), employees.getSalary());

		return new ResponseEntity<Object>(ResponseEntityCode.getReturnObjectString("updated successfully"),
				HttpStatus.OK);

	}

	@GetMapping("/getEmployeePagination")
	public ResponseEntity<List<Employee>> getAllEmployees2(@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("sortBy") String sortBy,
			@RequestParam("fieldName") String fieldName) {
		List<Employee> list = service.getAllEmployees(pageNo, pageSize, sortBy, fieldName);

		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}
