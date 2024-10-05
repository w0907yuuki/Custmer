package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.CustomerDeleteResult;
import com.example.demo.dto.CustomerInfo;
import com.example.demo.dto.CustomerSearchInfo;

/*
 * 顧客リスト画面serviceクラス
 * 
 * 
 * */

@Service
public interface CustomerService {

	/*
	 * 顧客情報すべて取得する
	 * 
	 * @return　すべての顧客情報
	 * */
	public List<CustomerInfo> getAllcustomer();
	
	/*
	 * 検索条件に合う顧客情報を取得
	 * 
	 * @return 条件に合う顧客情報
	 * */
	public List<CustomerInfo> editCustomerListByParam(CustomerSearchInfo dto);
	
	/*
	 * 指定した顧客情報を削除
	 * 
	 * @return 実行結果
	 * */
	public CustomerDeleteResult deleteCustomerInfoById(Long customerid);
}
