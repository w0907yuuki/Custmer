package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.constant.RegistResult;
import com.example.demo.dto.CustomerInfo;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistCustomerServiceImpl implements RegistCustomerService {
	/** ユーザー情報テーブルRepositoryクラス */
	private final CustomerRepository repository;

	/** メッセージソース */
	//private final MessageSource messageSource;

	@Override
	public RegistResult regist(CustomerInfo dto) {
		var userInfoOpt = repository.findByName(dto.getName());
		System.out.println(userInfoOpt);
		if (!userInfoOpt.isEmpty()) {
			return RegistResult.FAILURE_BY_SIGNUP_PROCEEDING;
		}
		var registInfo = registCustomerInfo(dto);
		try {
			repository.save(registInfo);
			} catch (Exception e) {
				return RegistResult.FAILURE_BY_DB_ERROR;
			}
		
		return RegistResult.SUCCEED;
	}
	
	private Customer registCustomerInfo(CustomerInfo dto) {
		System.out.println(dto.isState() +"セット");
		var customerInfo = new Customer();
		customerInfo.setCustomerid(dto.getCustomerid());
		customerInfo.setName(dto.getName());
		customerInfo.setState(dto.isState());
		customerInfo.setStart_time(LocalDateTime.now());		
		return customerInfo;
	}
}