package com.example.demo.dto;

import com.example.demo.constant.db.CustomerStateKind;

import lombok.Data;

//顧客検索DTOクラス
@Data
public class CustomerSearchInfo {

	 //顧客ID
	 private Long id;
	
	 //顧客名
	 private String name;
	 
	 //取引状態
	 private CustomerStateKind state;
	 
	
}
