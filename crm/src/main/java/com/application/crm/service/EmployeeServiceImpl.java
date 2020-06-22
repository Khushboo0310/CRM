package com.application.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.application.crm.thymeleafdemo.dao.EmployeeRepository;
import com.application.crm.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> search(String name) {
		// TODO Auto-generated method stub
		List<Employee> searchedEmp = null;
		
		if(name != null && !name.isEmpty() && !name.trim().isEmpty()) {
			searchedEmp = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(name, name);
			System.out.println(">>>>>>>>>>>Search result "+searchedEmp);
			if(searchedEmp.isEmpty()) {
				searchedEmp = employeeRepository.findAll();
			}
		}else {
			searchedEmp = employeeRepository.findAll();
		}
		return searchedEmp;
	}

	@Override
	public Object findSortedEmp() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(theId);
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> result = employeeRepository.findById(id);
		
		Employee employee = null;
		if(result.isPresent()) {
			employee = result.get();
		}
		else {
			throw new RuntimeException("Did not found Employee Id : "+id);
		}
		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		employeeRepository.save(theEmployee);
	}

}
