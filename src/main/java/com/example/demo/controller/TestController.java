package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Student;
import com.example.demo.entity.Test;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.secu.CustomUserDetails;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	TestRepository tr;
	@Autowired
	StudentRepository sr;

	@RequestMapping("/selectTest")
	String selectTest() {
		return "test/selectTest";
	}

	@RequestMapping("/test/{tno}")
	String test(@PathVariable("tno") int tno, Model model) {
		model.addAttribute("tno", tno);
		return "test/test" + tno;
	}

	@RequestMapping("/submit")
	String test111(@RequestBody Test test) {
		System.out.println(test.getAnswer1());
		System.out.println(test.getAnswer2());
		System.out.println(test.getAnswer3());
		System.out.println(test.getAnswer4());
		System.out.println(test.getAnswer5());
		return null;
	}
	//@RequestMapping("/submit")
	String submit(Test test, Model model) {
		// tno 들어오는 것엔 이상 x
		Optional<Test> result = tr.findById(test.getTno());
		Test answer = result.get();
		int cnt = 0;
		if (test.getAnswer1() == answer.getAnswer1()) {
			cnt++;
		}
		if (test.getAnswer2() == answer.getAnswer2()) {
			cnt++;
		}
		if (test.getAnswer3() == answer.getAnswer3()) {
			cnt++;
		}
		if (test.getAnswer4() == answer.getAnswer4()) {
			cnt++;
		}
		if (test.getAnswer5() == answer.getAnswer5()) {
			cnt++;
		}
		System.out.println("맞힌 개수 = " + cnt);
		cnt = cnt * 20;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Optional<Student> findStudent = sr.findById(userDetails.getUsername());
		Student student = findStudent.get();

		switch (test.getTno()) {
		case 1:
			student.setKorean(cnt);
			break;
		case 2:
			student.setEnglish(cnt);
			break;
		case 3:
			student.setMath(cnt);
			break;
		case 4:
			student.setSpring(cnt);
			break;
		}
		sr.save(student);
		model.addAttribute("student", student);
		model.addAttribute("msg", student.getName() + " 님 수고하셨습니다 :)");
		return "student/studentMain";
	}

	@RequestMapping("/result")
	String result(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Optional<Student> findStudent = sr.findById(userDetails.getUsername());
		Student student = findStudent.get();
		model.addAttribute("student", student);
		return "test/result";
	}
}
