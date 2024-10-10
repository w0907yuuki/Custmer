package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.constant.RegistResult;
import com.example.demo.constant.db.CustomerStateKind;
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

	//登録処理
	@Override
	public RegistResult regist(CustomerInfo dto) {
		var userInfoOpt = repository.findByNameLike(dto.getName());
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
	/**
	 * ユーザー登録情報を作成する。
	 * 
	 * @param dto 顧客登録画面Service入力情報
	 * @return 顧客情報
	 */
	private Customer registCustomerInfo(CustomerInfo dto) {
		System.out.println(dto.getState() +"セット");
		var customerInfo = new Customer();
		customerInfo.setCustomerid(dto.getCustomerid());
		customerInfo.setName(dto.getName());
		customerInfo.setState(CustomerStateKind.fromDisplayValue(dto.getState()));
		customerInfo.setStart_time(LocalDateTime.now());		
		return customerInfo;
	}
}