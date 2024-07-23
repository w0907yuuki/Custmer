package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerInfo {

	private Long id;

	private String name;

	private String start_time;

	private Boolean isState;
}
