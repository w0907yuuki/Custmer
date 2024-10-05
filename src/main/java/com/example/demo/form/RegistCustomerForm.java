package com.example.demo.form;

import lombok.Data;

@Data
public class RegistCustomerForm {

	//顧客ID
	private String id;
	//顧客名
	private String name;
	//取引状態
	private boolean state;
	
}
