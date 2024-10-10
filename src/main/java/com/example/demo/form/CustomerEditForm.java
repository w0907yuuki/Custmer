package com.example.demo.form;

import com.example.demo.constant.db.CustomerStateKind;

import lombok.Data;

@Data
public class CustomerEditForm {
	
	//顧客名
	private String name;
	
	//取引状態
	private CustomerStateKind state;
	
} 
