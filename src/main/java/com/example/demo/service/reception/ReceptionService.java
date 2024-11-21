package com.example.demo.service.reception;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.ReceptionDeleteResult;
import com.example.demo.dto.reception.ReceptionInfo;
import com.example.demo.dto.reception.ReceptionSearchInfo;

@Service
public interface ReceptionService {

	List<ReceptionInfo> getAllreception();
	
	List<ReceptionInfo> editReceptionListByParam(ReceptionSearchInfo dto);
	
	ReceptionDeleteResult deleteReceptionInfoById(Long id);
	
	
}
