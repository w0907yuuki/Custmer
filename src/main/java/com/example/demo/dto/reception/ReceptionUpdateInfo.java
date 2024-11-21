package com.example.demo.dto.reception;

import java.sql.Time;

import lombok.Data;

@Data
public class ReceptionUpdateInfo {

	private Long id;
	
	private String name;
	
	private String course;
	
	private Long count;
	
	private Time time;
	
	private Boolean state;
	
	//private String email;
	
	 @Override
     public String toString() {
         return "ReceptionUpdateInfo{" +
                 "name='" + name + '\'' +
                 //", email='" + email + '\'' +
                 '}';
     }
}
