package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerUpdateInfo {

	//顧客ID
	public Long customerid;
	//顧客名
	public String name;
	//取引状態
	public Boolean state;

}
