package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.form.CustomerForm;
import com.example.demo.service.CustomerService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//finalフィールドや@NonNullアノテーションが付けられたフィールドを持つ
//コンストラクタを自動的に生成
public class CustomerController {

	private final CustomerService service;
	
	private final HttpSession session;

	@GetMapping(UrlConst.CUSTOMER)
	public String View(Model model,CustomerForm customerForm) {
 		var custmer = service.getAllcustomer();
		model.addAttribute("customerList",custmer);
		return "customer"; 
	}
	
	@PostMapping(value = UrlConst.CUSTOMER, params = "regist")
	public String registCustomer(CustomerForm customerForm) {
		return UrlConst.CUSTOMER_REGIST;
	}
	
	@PostMapping(value = UrlConst.CUSTOMER, params = "edit")
	public String updateUser(CustomerForm form) {
		System.out.println("edit");
		System.out.println(form.getSelectedcustomerid());
		session.setAttribute(SessionKeyConst.SELECTED_CUSTOMER_ID, form.getSelectedcustomerid());
		return AppUtil.doRedirect(UrlConst.CUSTOMER_EDIT);
	}


}
