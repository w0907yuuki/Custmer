package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerEditInfo {
	
	//顧客名
	private String name;
	
	//取引状態
	private Boolean isState;
}
