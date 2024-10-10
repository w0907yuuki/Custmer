package com.example.demo.constant.db;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerStateKind {

	ENABLE(true,"取引中"),
	
	DISABLE(false,"取引終了");
	
	private boolean isState;
	
	private String displayValue;
	
	//enumの値をdisplayValueに基づいて返す
	public static CustomerStateKind fromDisplayValue(String displayValue) {
	        for (CustomerStateKind state : CustomerStateKind.values()) {
	            if (state.getDisplayValue().equals(displayValue)) {
	                return state;
	            }
	        }
	        throw new IllegalArgumentException("Unknown display value: " + displayValue);
	    }
}
