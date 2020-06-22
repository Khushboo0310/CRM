package com.application.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.crm.service.EmployeeService;
import com.application.crm.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	public List<String> status = null;
	
	@PostConstruct
	public void addData() {
		status = new ArrayList<String>();
		status.add("Active");
		status.add("Pending");
		status.add("Inactive");
	}

	@GetMapping("/")
	public String showEmployeeList(Model model) {
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("status", status);
		return "employees/list-employees";
	}
	
	@GetMapping("/search")
	public String searchItem(@RequestParam("employeeName") String name, Model model) {
		
		List<Employee> searchedEmp = employeeService.search(name);
		
		model.addAttribute("employees", searchedEmp);
		
		return "/employees/list-employees";
	}
	
	@GetMapping("/sort")
	public String sortItem(Model model) {
		
		model.addAttribute("employees", employeeService.findSortedEmp());
		
		return "/employees/list-employees";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		// delete the employee
		employeeService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/employees/";
		
	}
	
	@GetMapping("/findOne")
	@ResponseBody
	public Employee findOne(@RequestParam("id") int id) {
		return employeeService.findById(id);
	}
	
	@PostMapping("/save")
	public String saveEmployee(Employee theEmployee) {
		
		System.out.println(">>>>>>>>> Employee"+theEmployee);
		// save the employee
		employeeService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/";
	}
	
}
