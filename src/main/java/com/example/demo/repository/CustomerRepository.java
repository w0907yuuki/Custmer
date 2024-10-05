package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	/**
	 * 顧客IDの完全一致検索
	 * 
	 * @param 顧客ID
	 * @return 検索でヒットした顧客情報のリスト
	 */
	List<Customer> findByCustomerid(Long customerid);  
	/**
	 * 顧客名の部分一致検索
	 * 
	 * @param 顧客名
	 * @return 検索でヒットした顧客情報のリスト
	 */
	List<Customer> findByNameLike(String customername);  
	/**
	 * 取引状態の完全一致検索
	 * 
	 * @param 取引状態　state
	 * @return 検索でヒットした顧客情報のリスト
	 */
	List<Customer> findByisState(Boolean isState); 
	
	
} 