package com.application.crm.service;

import java.util.List;

import com.application.crm.thymeleafdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public List<Employee> search(String name);

	public Object findSortedEmp();

	public void deleteById(int theId);

	public Employee findById(int id);

	public void save(Employee theEmployee);
}
