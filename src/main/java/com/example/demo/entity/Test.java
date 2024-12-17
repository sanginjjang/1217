package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tno;
	@Column(nullable = false)
	int answer1;
	@Column(nullable = false)
	int answer2;
	@Column(nullable = false)
	int answer3;
	@Column(nullable = false)
	int answer4;
	@Column(nullable = false)
	int answer5;
}
