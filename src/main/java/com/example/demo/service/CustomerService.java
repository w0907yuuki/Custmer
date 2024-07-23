package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerInfo;

@Service
public interface CustomerService {

	public List<CustomerInfo> getAllcustomer();
}
