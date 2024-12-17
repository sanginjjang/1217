package com.example.demo.secu;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Student;

import lombok.Data;
@Data
public class CustomUserDetails implements UserDetails {

	private Student student;

	public CustomUserDetails(Student student) { //생성자 주입
		this.student = student;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){ //그렌티드 어토리티를 상속받거나 구현체인 애만 들어올 수 있다
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getAuthority() {
				return student.getRole();
			}
		});
		
		return collection; //여기가 null이면 안돼 //첫번째 자주하는 실수
	}

	@Override
	public String getPassword() {
		return student.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return student.getUsername();
	}

	public String getRole() {
		return student.getRole();
	}

	public String getName() {
		return student.getName();
	}

}
