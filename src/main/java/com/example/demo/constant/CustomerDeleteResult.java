package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerDeleteResult {

	/* エラーなし */
	SUCCEED(MessageConst.USERLIST_DELETE_SUCCEED),

	/* エラーあり */
	ERROR(MessageConst.USERLIST_NON_EXISTED_LOGIN_ID);

	/** メッセージID */
	private String messageId;

	
	
}
