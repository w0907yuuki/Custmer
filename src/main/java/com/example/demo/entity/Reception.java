package com.example.demo.entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="reception")
@Data
@AllArgsConstructor
public class Reception {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long receptionid;
	
	@Column(name="name")
	private String name;
	@Column(name="course")
	private String course;
	@Column(name="count")
	private Long count;
	@Column(name="time")
	private Time time;
	@Column(name="state")
	private Boolean state;

	public Reception(){
		
	}
	
}
