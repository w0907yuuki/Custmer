package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerInfo;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	@Override
	public List<CustomerInfo>  getAllcustomer() {
		return toCustmerInfos(repository.findAll());
	}

	private List<CustomerInfo> toCustmerInfos(List<Customer> userInfos) {
		var userListInfos = new ArrayList<CustomerInfo>();
		for (Customer userInfo : userInfos) {
			var userListInfo = mapper.map(userInfo, CustomerInfo.class);
			userListInfos.add(userListInfo);
		}

		return userListInfos;
	}
}
