package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.CustomerEditResult;
import com.example.demo.dto.CustomerUpdateInfo;
import com.example.demo.entity.Customer;

public interface CustomerEditService {
	
	public Optional<Customer> serchCustomerInfo(Long customerid);

	
	public CustomerEditResult updateCustomerInfo(CustomerUpdateInfo customerUpdateInfo);
}
