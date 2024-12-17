package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	StudentRepository sr;
	
	@RequestMapping("/main")
	String main(Model model) {
		List<Student> list = sr.findAll();
		model.addAttribute("list", list);
		return "admin/adminMain";
	}

}
