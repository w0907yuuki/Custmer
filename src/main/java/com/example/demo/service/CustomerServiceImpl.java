package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.CustomerDeleteResult;
import com.example.demo.dto.CustomerInfo;
import com.example.demo.dto.CustomerSearchInfo;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	/**顧客情報テーブルDAO*/
	private final CustomerRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	@Override
	public List<CustomerInfo>  getAllcustomer() {
		return toCustomerInfos(repository.findAll());
	}


	@Override
	public List<CustomerInfo> editCustomerListByParam(CustomerSearchInfo dto) {
		return toCustomerInfos(findCustomerInfoByParam(dto));
	}

	/**
	 * 指定した顧客情報を削除する
	 * 
	 * @return　実行結果
	 * */
	
	@Override
	public CustomerDeleteResult deleteCustomerInfoById(Long customerid) {
		var customerInfo = repository.findById(customerid);
		if (customerInfo.isEmpty()) {
			return CustomerDeleteResult.ERROR;
		}
		repository.deleteById(customerid);
		
		return CustomerDeleteResult.SUCCEED;  
	}
	/*顧客情報の条件検索を行い、検索結果を返す
	 * 
	 * 
	 * @return 検索結果
	 * */
	private List<Customer> findCustomerInfoByParam(CustomerSearchInfo dto){ 
		
		if(dto.getName() != null && !dto.getName().isEmpty() ) 
		{
			return repository.findByNameLike("%" + dto.getName() + "%" ); 
		}else if (dto.getId() != null) {
			return repository.findByCustomerid(dto.getId());
		}
		else {
			return repository.findAll();
		}
	}
	/*
	 * 顧客情報EntityのListを顧客リスト情報DTOのListに変換
	 * 
	 * @return　顧客リスト情報DTOのList
	 * 
	 * */
	private List<CustomerInfo> toCustomerInfos(List<Customer> customerInfos) {
		var customerListInfos = new ArrayList<CustomerInfo>();
		for (Customer customerInfo : customerInfos) {
			var customerListInfo = mapper.map(customerInfo, CustomerInfo.class);
			customerListInfo.setState(customerInfo.getState().getDisplayValue());
			customerListInfos.add(customerListInfo);
		}

		return customerListInfos;
	}
}
