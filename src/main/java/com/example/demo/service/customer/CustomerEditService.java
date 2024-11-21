package com.example.demo.service.customer;

import java.util.Optional;

import com.example.demo.dto.customer.CustomerEditResult;
import com.example.demo.dto.customer.CustomerUpdateInfo;
import com.example.demo.entity.Customer;

public interface CustomerEditService {
	
	Optional<Customer> serchCustomerInfo(Long customerid);

	
	CustomerEditResult updateCustomerInfo(CustomerUpdateInfo customerUpdateInfo);
}
