package com.example.demo.dto;

import com.example.demo.constant.CustomerEditMessage;
import com.example.demo.entity.Customer;

import lombok.Data;

@Data
public class CustomerEditResult {
	
	private Customer updateCustomerInfo;
	
	private CustomerEditMessage updateMessage;

}
