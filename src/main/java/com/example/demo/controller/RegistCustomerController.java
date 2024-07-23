package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.RegistResult;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.dto.CustomerInfo;
import com.example.demo.form.RegistCustomerForm;
import com.example.demo.service.RegistCustomerService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistCustomerController {
	/** ユーザー登録画面Serviceクラス */
	private final RegistCustomerService service;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** オブジェクト間項目輸送クラス */
	private final Mapper mapper;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "registcustomerForm";
	
	@GetMapping("/registcustomer")
	public String view(Model model) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if (isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME, new RegistCustomerForm());
		}
		return "registcustomer";
	} 
	@PostMapping("/registcustomer")
	public String regist(@Validated RegistCustomerForm registcustomerForm,BindingResult result,RedirectAttributes redirectAttributes) {
		
		//errorの場合エラーメッセージを表示
		if(result.hasErrors()) {
			System.out.println(result.hasErrors() +"error");
			System.out.println("Validation errors found: " + result.getAllErrors());
			return AppUtil.doRedirect("registercustomer");
		}
		
		var registResult = service.regist(mapper.map(registcustomerForm,CustomerInfo.class));
		System.out.println(registResult);
		var isError = registResult != RegistResult.SUCCEED;
		System.out.println(isError);
		if (isError) {
			editGuideMessage(registcustomerForm, result, registResult.getMessageId(), redirectAttributes);
			return AppUtil.doRedirect("registcustomer");
		}
		
		session.setAttribute(SessionKeyConst.SELECETED_LOGIN_ID, registcustomerForm.getId());
		editGuideMessage(registcustomerForm, result, registResult.getMessageId(), redirectAttributes);
		return AppUtil.doRedirect("registcustomer");
	}
	 
	
		private void editGuideMessage(RegistCustomerForm registcustomerForm, BindingResult bdResult, String messageId,
				RedirectAttributes redirectAttributes) {
			redirectAttributes.addFlashAttribute("message", AppUtil.getMessage(messageSource, messageId));
			redirectAttributes.addFlashAttribute("isError", true);
			redirectAttributes.addFlashAttribute(registcustomerForm);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + FORM_CLASS_NAME, bdResult);
		}
	}