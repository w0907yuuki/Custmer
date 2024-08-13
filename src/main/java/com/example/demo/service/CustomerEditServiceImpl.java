package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.CustomerEditMessage;
import com.example.demo.dto.CustomerEditResult;
import com.example.demo.dto.CustomerUpdateInfo;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerEditServiceImpl  implements CustomerEditService{

	private final CustomerRepository repository;
	
	
	@Override
	public Optional<Customer> serchCustomerInfo(Long customerid){
		return repository.findById(customerid);
	}
	
	@Override
	public CustomerEditResult updateCustomerInfo(CustomerUpdateInfo customerUpdateInfo) {
		var customerUpdateResult = new CustomerEditResult();
		
		var updateInfoOpt = repository.findById(customerUpdateInfo.getCustomerid());
		System.out.println("updateInfoOpt ="+updateInfoOpt);
		if(updateInfoOpt.isEmpty()) {
			customerUpdateResult.setUpdateMessage(CustomerEditMessage.FAILED);
			return customerUpdateResult;
		}
		
		var updateInfo = updateInfoOpt.get();
		updateInfo.setIsState(customerUpdateInfo.isState());
		System.out.println("updateInfo = " +updateInfo);
		try {
			repository.save(updateInfo);
		}catch(Exception e) {
			customerUpdateResult.setUpdateMessage(CustomerEditMessage.FAILED);
			return customerUpdateResult;
		}
		
		customerUpdateResult.setUpdateCustomerInfo(updateInfo);
		customerUpdateResult.setUpdateMessage(CustomerEditMessage.SUCCEED);
		return customerUpdateResult;
		
		
	}
	
	
}
