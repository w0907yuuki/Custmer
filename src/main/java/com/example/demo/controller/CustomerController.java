package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.CustomerDeleteResult;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.CustomerSearchInfo;
import com.example.demo.form.CustomerForm;
import com.example.demo.service.CustomerService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//finalフィールドや@NonNullアノテーションが付けられたフィールドを持つ
//コンストラクタを自動的に生成
public class CustomerController {

	private final CustomerService service;
	
	private final HttpSession session;
	
	private final MessageSource messageSource;
	
	private final Mapper mapper;

	@GetMapping(UrlConst.CUSTOMER)
	public String View(Model model,CustomerForm customerForm) {
 		var custmer = service.getAllcustomer(); 
		model.addAttribute("customerList",custmer);
		return ViewNameConst.CUSTOMER_LIST; 
	}
	
	/* 検索機能　ボタン押下時実行
	   @return 検索条件に合う顧客情報 */
	@PostMapping(value = UrlConst.CUSTOMER, params = "search")
	public String searchCustomer(Model model , CustomerForm customerForm) { 		
		var searchDto = mapper.map(customerForm,CustomerSearchInfo.class);
		var customerInfos = service.editCustomerListByParam(searchDto);
		
		model.addAttribute("customerList",customerInfos);
		return ViewNameConst.CUSTOMER_LIST; 
	}
	
	/* 顧客情報　登録
	   @return 登録画面URL*/
	@PostMapping(value = UrlConst.CUSTOMER, params = "regist")
	public String registCustomer(Model model,CustomerForm customerForm) {
		model.addAttribute("registcustomerForm", new CustomerForm());
		return ViewNameConst.CUSTOMER_LIST_REGIST;
	} 
	
	/* 顧客情報　編集
	   @return 編集画面URL*/
	@PostMapping(value = UrlConst.CUSTOMER, params = "edit")
	public String updateCustomer(CustomerForm form) {
		session.setAttribute(SessionKeyConst.SELECTED_CUSTOMER_ID, form.getSelectedcustomerid());
		return AppUtil.doRedirect(UrlConst.CUSTOMER_EDIT);
	}

	/* 顧客情報　削除
	   @return 顧客リスト*/
	@PostMapping(value = UrlConst.CUSTOMER, params = "delete")
	public String deleteUser(Model model, CustomerForm form) {
		var executeResult = service.deleteCustomerInfoById(form.getSelectedcustomerid());
		model.addAttribute("isError", executeResult == CustomerDeleteResult.ERROR);
		model.addAttribute("message", AppUtil.getMessage(messageSource, executeResult.getMessageId()));

		// 削除後、フォーム情報の「選択されたログインID」は不要になるため、クリアします。
		return searchCustomer(model, form.clearSelecteduserid());
	}


}
