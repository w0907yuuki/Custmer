package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerInfo {

	//顧客ID
	private Long customerid;
	//顧客名
	private String name;
	//取引開始時間
	private String start_time;
	//取引状態
	private String state;
}
