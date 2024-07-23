package com.example.demo.form;

import lombok.Data;

@Data
public class CustomerForm {

	private String id;

	private String name;

	private Boolean state;

	private String selectedcustomerid;

	public CustomerForm clearSelecteduserid() {
		this.selectedcustomerid = null;

		return this;
	}

}
