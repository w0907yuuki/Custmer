package com.example.demo.form.reception;

import java.sql.Time;

import lombok.Data;

@Data
public class ReceptionForm {

	private Long receptionid;
	
	private String name;
	
	private String course;
	
	private Long count;
	
	private Time time;
	
	private Boolean state;
	
	private Long selectedreceptionid;
	
	public ReceptionForm clearSelectedreceptionid() {
		this.selectedreceptionid= null;

		return this;
	}
}
