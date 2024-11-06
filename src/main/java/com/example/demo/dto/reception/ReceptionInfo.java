package com.example.demo.dto.reception;

import java.sql.Time;

import lombok.Data;

@Data
public class ReceptionInfo {
	
	private Long receptionid;
	
	private String name;
	
	private String course;
	
	private Long count;
	
	private Time time;
	
	private Boolean state;
	
}
