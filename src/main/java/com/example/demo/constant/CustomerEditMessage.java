package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerEditMessage {

	SUCCEED(MessageConst.CUSTOMEREDIT_CUSTOMER_SUCCEED),
	
	FAILED(MessageConst.CISTOMEREDIT_CUSTOMER_FAILED);
	
	private String messageId;
}
