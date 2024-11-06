package com.example.demo.service.reception;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.ReceptionDeleteResult;
import com.example.demo.dto.reception.ReceptionInfo;
import com.example.demo.dto.reception.ReceptionSearchInfo;

@Service
public interface ReceptionService {

	public List<ReceptionInfo> getAllreception();
	
	public List<ReceptionInfo> editReceptionListByParam(ReceptionSearchInfo dto);
	
	public ReceptionDeleteResult deleteReceptionInfoById(Long id);
	
	
}
