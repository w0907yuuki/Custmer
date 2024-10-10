package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.demo.constant.db.CustomerStateKind;
import com.example.demo.entity.converter.CustomerStateConverter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="custmer")
@Data
@AllArgsConstructor
public class Customer {

	@Id
	@Column(name="custmerid")
	//自動ID生成
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerid;

	@Column(name="custmername")
	private String name;

	@Column(name="start_time")
	private LocalDateTime start_time;
	
	@Column(name="state")
	@Convert(converter = CustomerStateConverter.class)
	//@Convert(converter = CustomerStateConverter.class)
	private CustomerStateKind state;
	
	public Customer() {
		
	}
}
