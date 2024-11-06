package com.example.demo.service.reception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.ReceptionDeleteResult;
import com.example.demo.dto.reception.ReceptionInfo;
import com.example.demo.dto.reception.ReceptionSearchInfo;
import com.example.demo.entity.Reception;
import com.example.demo.repository.reception.ReceptionRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceptionServiceImpl implements ReceptionService{
	
	private final ReceptionRepository repository;
	
	private final Mapper mapper;
	
	@Override
	public List<ReceptionInfo>getAllreception(){
		return toReceptionInfos(repository.findAll());
	}
	
	@Override
	public List<ReceptionInfo> editReceptionListByParam(ReceptionSearchInfo dto){
		return toReceptionInfos(findReceptionInfoByParam(dto));
	}
	
	@Override
	public ReceptionDeleteResult deleteReceptionInfoById(Long id) {
		var receptionInfo = repository.findById(id);
		if (receptionInfo.isEmpty()) {
			return ReceptionDeleteResult.ERROR;
		}
		repository.deleteById(id);

		return ReceptionDeleteResult.SUCCEED;
	}
	
	private List<Reception> findReceptionInfoByParam(ReceptionSearchInfo dto){
		if(dto.getName() != null && !dto.getName().isEmpty()) {
			return repository.findByNameLike("%" + dto.getName() + "%");
		}else {
			return repository.findAll();
			}
		
	
	}
	
	private List<ReceptionInfo> toReceptionInfos(List<Reception> receptionInfos){
		var reseptionListInfos = new ArrayList<ReceptionInfo>();
		for (Reception receptionInfo : receptionInfos) {
			var receptionListInfo =mapper.map(receptionInfo, ReceptionInfo.class);
			//receptionInfo.setState();
			reseptionListInfos.add(receptionListInfo);
		}
		
		return reseptionListInfos;
	}

}
