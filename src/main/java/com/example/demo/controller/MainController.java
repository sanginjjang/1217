package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestRepository;

@Controller
public class MainController {
	@Autowired
	BCryptPasswordEncoder bcpe;

	@Autowired
	StudentRepository sr;

	@Autowired
	TestRepository tr;

	@RequestMapping("/")
	String root() {
		return "main";
	}

	@GetMapping("/loginForm")
	public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("errormsg", "아이디나 비밀번호를 확인해 주십시오.");
		}
		return "loginForm";
	}

	@RequestMapping("/registForm")
	String registForm() {
		return "registForm";
	}

	@RequestMapping("/registStudent")
	String registStudent(Student student) {
		String encodedPassword = bcpe.encode(student.getPassword());
		student.setPassword(encodedPassword);
		student.setRole("ROLE_ADMIN");
		sr.save(student);
		return "main";
	}

	@GetMapping("/loginProc")
	public String loginProc(@RequestParam(value = "error", required = false) String error, Model model) {
		
		if (error != null) {
			model.addAttribute("errormsg", "아이디나 비밀번호를 확인해 주십시오.");
			return "/loginForm";
		}
		return "redirect:/";
	}

}
