package com.example.demo.controller.reservation;

import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.constant.ReceptionDeleteResult;
import com.example.demo.dto.reception.ReceptionSearchInfo;
import com.example.demo.form.reception.ReceptionForm;
import com.example.demo.service.reception.ReceptionService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ReceptionManagementController {

	private final  ReceptionService service;
	
	//private final HttpSession session;
	
	private final MessageSource messageSource;
	
	private final Mapper mapper;
	
	@GetMapping("/receptionmanagement")
	public String view(Model model,ReceptionForm receptionForm) {
		var reception = service.getAllreception();
		model.addAttribute("receptionList",reception);
		model.addAttribute("receptionForm", receptionForm);
		System.out.println(reception);
		return "reservation/receptionmanagement";
	}
	
	@PostMapping(value = "/receptionmanagement",params = "search")
	public String searchReception(Model model ,ReceptionForm receptionForm) {
		var searchDto = mapper.map(receptionForm, ReceptionSearchInfo.class);
		var receptionInfos = service.editReceptionListByParam(searchDto);
		model.addAttribute("receptionList",receptionInfos);
		return "reservation/receptionmanagement";
	}
	
	@PostMapping(value="/receptionmanagement", params="regist")
	public String registReception(Model model,ReceptionForm receptionForm) {

		model.addAttribute("registreceptionForm",new ReceptionForm());
		return "reservation/reception";
	}
	
	/*@PostMapping(value="/receptionmanagement" , params="edit")
	public String updateReception(ReceptionForm receptionForm) {
		session.setAttribute(SessionKeyConst.SELECTED_CUSTOMER_ID, receptionForm.getSelectedreceptionid());
		return AppUtil.doRedirect("reservation/editreception");
	}*/
	@PostMapping("/receptionmanagement")
    public ResponseEntity<String> updateReceptionManagement(@RequestBody Map<String, Object> changedData) {
        // データを保存する処理
        return ResponseEntity.ok("データが更新されました");
    }
	@PostMapping(value = "/receptionmanagement",params = "delete")
	public String deleteReception(Model model,ReceptionForm receptionForm) {
		System.out.println(receptionForm.getSelectedreceptionid());
		var executeResult = service.deleteReceptionInfoById(receptionForm.getSelectedreceptionid());
		//
		model.addAttribute("isError", executeResult == ReceptionDeleteResult.ERROR);
		model.addAttribute("message", AppUtil.getMessage(messageSource, executeResult.getMessageId()));

		
		return searchReception(model,receptionForm.clearSelectedreceptionid());
	}
}

