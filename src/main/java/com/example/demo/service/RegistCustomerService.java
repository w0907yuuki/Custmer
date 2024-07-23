package com.example.demo.service;

import com.example.demo.constant.RegistResult;
import com.example.demo.dto.CustomerInfo;

public interface RegistCustomerService {

	public RegistResult regist(CustomerInfo dto);
}
