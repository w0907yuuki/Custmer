package com.example.demo.service.customer;

import java.util.Optional;

import com.example.demo.dto.customer.CustomerEditResult;
import com.example.demo.dto.customer.CustomerUpdateInfo;
import com.example.demo.entity.Customer;

public interface CustomerEditService {
	
	public Optional<Customer> serchCustomerInfo(Long customerid);

	
	public CustomerEditResult updateCustomerInfo(CustomerUpdateInfo customerUpdateInfo);
}
