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
	/** 顧客情報テーブルRepository */
	private final CustomerRepository repository;
	
	
	@Override
	public Optional<Customer> serchCustomerInfo(Long customerid){
		return repository.findById(customerid);
	}
	
	@Override
	public CustomerEditResult updateCustomerInfo(CustomerUpdateInfo customerUpdateInfo) {
		var customerUpdateResult = new CustomerEditResult();
		
		// 現在の登録情報を取得
		var updateInfoOpt = repository.findById(customerUpdateInfo.getCustomerid());
		System.out.println("updateInfoOpt ="+updateInfoOpt);
		if(updateInfoOpt.isEmpty()) {
			customerUpdateResult.setUpdateMessage(CustomerEditMessage.FAILED);
			return customerUpdateResult;
		}
		// 画面の入力情報等をセット
		var updateInfo = updateInfoOpt.get();
		updateInfo.setState(customerUpdateInfo.state);
		updateInfo.setName(customerUpdateInfo.name);
		System.out.println("updateInfo = " +updateInfo);
		//顧客情報編集したデータをupdateする
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
