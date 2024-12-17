package com.example.demo.secu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

//customUserDetail을 관리해 줄 친구
@Service //두번째 실수 //이거 안붙이면 안돼!!!!!!!!!!!!!!!!!!!!!!!!
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	StudentRepository sr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//UserDetails 는 인터페이스 == 반환체가 인터페이스!!!!!
		//아이디만 
		Optional<Student> result = sr.findById(username);
		Student student = result.get();
		return new CustomUserDetails(student);
	}

}
