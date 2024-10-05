/*package com.example.demo.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.example.demo.constant.db.CustomerStateKind;

@Converter
public class CustomerStateConverter implements AttributeConverter<CustomerStateKind,Boolean> {

	@Override
	public Boolean convertToDatabaseColumn(CustomerStateKind customerState) {
		return customerState.isState();
	}
	
	@Override
	public CustomerStateKind convertToEntityAttribute(Boolean isState) {
		return isState ? CustomerStateKind.DISABLE : CustomerStateKind.ENABLE;
	}
	
}
*/