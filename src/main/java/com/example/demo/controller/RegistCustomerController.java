package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

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
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.CustomerInfo;
import com.example.demo.form.RegistCustomerForm;
import com.example.demo.service.RegistCustomerService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

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
	
	/**
	 * 画面の初期表示を行います。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 顧客登録画面
	 */
	@GetMapping(UrlConst.CUSTOMER_REGIST)
	public String view(Model model) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if (isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME, new RegistCustomerForm());
		}
		return ViewNameConst.CUSTOMER_LIST_REGIST;
	} 
	@PostMapping(UrlConst.CUSTOMER_REGIST)
	public String regist(@Validated RegistCustomerForm registcustomerForm,BindingResult result,RedirectAttributes redirectAttributes) {
		
		//errorの場合エラーメッセージを表示
		if(result.hasErrors()) {
			System.out.println(result.hasErrors() +"error");
			System.out.println("Validation errors found: " + result.getAllErrors());
			return AppUtil.doRedirect(ViewNameConst.CUSTOMER_LIST_REGIST);
		}
		//登録処理実行
		var registResult = service.regist(mapper.map(registcustomerForm,CustomerInfo.class));
		System.out.println(registResult);
		var isError = registResult != RegistResult.SUCCEED;
		System.out.println(isError);
		if (isError) {
			editGuideMessage(registcustomerForm, result, registResult.getMessageId(), redirectAttributes);
			return AppUtil.doRedirect(ViewNameConst.CUSTOMER_LIST_REGIST);
		}
		
		session.setAttribute(SessionKeyConst.SELECETED_LOGIN_ID, registcustomerForm.getId());
		editGuideMessage(registcustomerForm, result, registResult.getMessageId(), redirectAttributes);
		return AppUtil.doRedirect(ViewNameConst.CUSTOMER_LIST_REGIST);
	}
	 
	/**
	 * メッセージIDを使ってプロパティファイルからメッセージを取得し、画面に表示します。
	 * 
	 * <p>また、画面でメッセージを表示する際に通常メッセージとエラーメッセージとで色分けをするため、<br>
	 * その判定に必要な情報も画面に渡します。
	 * 
	 * @param form 入力情報
	 * @param bdResult 入力内容の単項目チェック結果
	 * @param messageId プロパティファイルから取得したいメッセージのID
	 * @param redirectAttributes リダイレクト用モデル
	 */
		private void editGuideMessage(RegistCustomerForm registcustomerForm, BindingResult bdResult, String messageId,
				RedirectAttributes redirectAttributes) {
			redirectAttributes.addFlashAttribute("message", AppUtil.getMessage(messageSource, messageId));
			redirectAttributes.addFlashAttribute("isError", true);
			redirectAttributes.addFlashAttribute(registcustomerForm);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + FORM_CLASS_NAME, bdResult);
		}
	}