package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.secu.CustomUserDetails;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentRepository sr;
	
	@RequestMapping("/main")
	String main(Model model) {
		
		
		
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Optional<Student> findStudent = sr.findById(userDetails.getUsername());
		Student student = findStudent.get();
		
		
		
		
		
		model.addAttribute("student", student);
		model.addAttribute("msg", student.getUsername() + "님 반갑습니다!");
		return "student/studentMain";
	}
	@RequestMapping("/deleteStudent/{username}")
	String deleteStudent(@PathVariable("username") String username) {
		sr.deleteById(username);
		return "redirect:/admin/main";
	}
}
