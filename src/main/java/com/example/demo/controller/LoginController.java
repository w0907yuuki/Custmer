package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService service;

	private final PasswordEncoder passwordEncoder;
	
	private final HttpSession session;
	//login.htmlを表示
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model,LoginForm loginForm) {

		return ViewNameConst.LOGIN;
	}
	@GetMapping(value=UrlConst.LOGIN,params="error")
	public String viewWithError(Model model,LoginForm loginForm) {
		var errorInfo =(Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg",errorInfo.getMessage());
		return ViewNameConst.LOGIN;
	}
	
	//Postが送られたら実行する
	//入力したIDとPasswordを照会する
	@PostMapping(UrlConst.LOGIN)
	public String login( Model model,LoginForm loginForm) {
		var user =service.serchUserById(loginForm.getUserid());
		var boluser = user.isPresent() && passwordEncoder.matches(loginForm.getPassword(),user.get().getPassword());
		if(boluser) {
			return "redirect:/topmenu";
		}else {
			model.addAttribute("errorMsg","エラー");
			return ViewNameConst.LOGIN;
		}
		
	}
}