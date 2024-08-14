package com.example.demo.form;

import lombok.Data;

@Data
public class CustomerForm {

	private String id;

	private String name;

	private boolean state;

	private Long selectedcustomerid;

	public CustomerForm clearSelecteduserid() {
		this.selectedcustomerid = null;

		return this;
	}

}
