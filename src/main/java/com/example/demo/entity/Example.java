package com.example.demo.entity;

import org.checkerframework.checker.units.qual.g;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_example")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Example {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tno;
	String example1;
	String example2;
	String example3;
	String example4;
}
