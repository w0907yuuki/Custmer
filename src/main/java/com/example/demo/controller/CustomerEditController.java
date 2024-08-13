package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.CustomerEditMessage;
import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.CustomerEditInfo;
import com.example.demo.dto.CustomerUpdateInfo;
import com.example.demo.entity.Customer;
import com.example.demo.form.CustomerEditForm;
import com.example.demo.service.CustomerEditService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerEditController {
	
	private final HttpSession session;
	
	private final CustomerEditService service;
	
	private final Mapper mapper;
	
	private final MessageSource messageSource;

	@GetMapping(UrlConst.CUSTOMER_EDIT)
	public String View(Model model) throws Exception {
		var customerid = (Long)session.getAttribute(SessionKeyConst.SELECTED_CUSTOMER_ID);
		var customerInfoOpt = service.serchCustomerInfo(customerid);
		if(customerInfoOpt.isEmpty()) {
			model.addAttribute("message",AppUtil.getMessage(messageSource, MessageConst.CUSTOMEREDIT_NON_EXISTED_CUSTOMER_ID));
		    return  "customeredit";
		}
		setupCommonInfo(model, customerInfoOpt.get());
		return "customeredit";
	} 
	
	@PostMapping(value = UrlConst.CUSTOMER_EDIT,params = "update")
	public String updateCustomer(Model model, CustomerEditForm form, @AuthenticationPrincipal Customer customer) {
		var updateDto = mapper.map(form, CustomerUpdateInfo.class);
		updateDto.setCustomerid((Long) session.getAttribute(SessionKeyConst.SELECTED_CUSTOMER_ID));
		var updateResult = service.updateCustomerInfo(updateDto);
		System.out.println(updateDto);
		System.out.println(updateResult);
		var updateMessage = updateResult.getUpdateMessage();
		if(updateMessage == CustomerEditMessage.FAILED) {
			model.addAttribute("message",AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			System.out.println("更新失敗");
			return "customeredit";
		}
		
		setupCommonInfo(model, updateResult.getUpdateCustomerInfo());
		
		model.addAttribute("isError",false);
		model.addAttribute("message", AppUtil.getMessage(messageSource,updateMessage.getMessageId()));
		System.out.println("更新終了");
		return "customeredit";
		
	}

	private void setupCommonInfo(Model model, com.example.demo.entity.Customer customerInfo) { 
		model.addAttribute("customerEditForm", mapper.map(customerInfo,CustomerEditForm.class));
		model.addAttribute("customerEditInfo",mapper.map(customerInfo,CustomerEditInfo.class));
		System.out.println(customerInfo.getCustomerid());
		System.out.println(customerInfo.getName());
	}
}
