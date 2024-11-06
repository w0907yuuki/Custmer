package com.example.demo.controller.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.form.reception.ReceptionForm;
import com.example.demo.service.reception.ReceptionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReceptionController {

	private final  ReceptionService service;
	
	@GetMapping("/reception")
	public String View(Model model,ReceptionForm receptionForm) {
		var reception = service.getAllreception();
		model.addAttribute("receptionList",reception);
		return "reservation/reception";
	}
}
