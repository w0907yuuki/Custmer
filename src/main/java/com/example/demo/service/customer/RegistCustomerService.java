package com.example.demo.service.customer;

import com.example.demo.constant.RegistResult;
import com.example.demo.dto.customer.CustomerInfo;

public interface RegistCustomerService {

	public RegistResult regist(CustomerInfo dto);
}
