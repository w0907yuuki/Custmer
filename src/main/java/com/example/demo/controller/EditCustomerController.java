package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;

@Controller
public class EditCustomerController {

	@GetMapping(UrlConst.CUSTOMER_EDIT)
	public String View(Model model) {
		return "editcustomer";
	} 
}
