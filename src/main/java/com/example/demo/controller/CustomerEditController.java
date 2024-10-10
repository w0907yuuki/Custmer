package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

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
import com.example.demo.constant.db.CustomerStateKind;
import com.example.demo.dto.CustomerEditInfo;
import com.example.demo.dto.CustomerUpdateInfo;
import com.example.demo.entity.Customer;
import com.example.demo.form.CustomerEditForm;
import com.example.demo.service.CustomerEditService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerEditController {
	/** セッションオブジェクト */
	private final HttpSession session;
	/**　顧客編集画面Serviceクラス */
	private final CustomerEditService service;
	/** Dozer Mapper */
	private final Mapper mapper;
	/** メッセージソース */
	private final MessageSource messageSource;
	/**
	 * 前画面で選択されたログインIDに紐づくユーザー情報を画面に表示します。
	 * 
	 * @param model モデル
	 * @return 表示画面
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.CUSTOMER_EDIT)
	public String View(Model model,CustomerEditForm form) throws Exception {
		var customerid = (Long)session.getAttribute(SessionKeyConst.SELECTED_CUSTOMER_ID);
		var customerInfoOpt = service.serchCustomerInfo(customerid);
		if(customerInfoOpt.isEmpty()) {
			model.addAttribute("message",AppUtil.getMessage(messageSource, MessageConst.CUSTOMEREDIT_NON_EXISTED_CUSTOMER_ID));
		    return  "customeredit";
		}
		setupCommonInfo(model, customerInfoOpt.get());
		return "customeredit";
	} 
	/**
	 * 画面の入力情報をもとにユーザー情報を更新します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
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
			return "customerediterror";
		}
		
		setupCommonInfo(model, updateResult.getUpdateCustomerInfo());
		
		model.addAttribute("isError",false);
		model.addAttribute("message", AppUtil.getMessage(messageSource,updateMessage.getMessageId()));
		System.out.println("更新終了");
		return "customeredit";
		
	}
	/**
	 * 画面表示に必要な共通項目の設定を行います。
	 * 
	 * @param model モデル
	 * @param editedForm 入力済みのフォーム情報
	 */
	private void setupCommonInfo(Model model, com.example.demo.entity.Customer customerInfo) { 
		model.addAttribute("customerEditForm", mapper.map(customerInfo,CustomerEditForm.class));
		model.addAttribute("customerEditInfo",mapper.map(customerInfo,CustomerEditInfo.class));
		model.addAttribute("customerStateOptions",CustomerStateKind.values());
		System.out.println(customerInfo.getCustomerid());
		System.out.println(customerInfo.getName());
		System.out.println(customerInfo.getState());
	}
}
