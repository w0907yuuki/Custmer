package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegistResult {

	SUCCEED(MessageConst.REGIST_CUETOMER_COMPLETE),

	/* 既に本登録済み */
	FAILURE_BY_ALREADY_COMPLETED(MessageConst.SIGNUP_ALREADY_COMPLETED),

	/* 仮登録状態 */
	FAILURE_BY_SIGNUP_PROCEEDING(MessageConst.SIGNUP_SIGNUP_PROCEEDING),

	/* DB更新エラー */
	FAILURE_BY_DB_ERROR(MessageConst.SIGNUP_DB_ERROR),

	/* メール送信失敗 */
	FAILURE_BY_MAIL_SEND_ERROR(MessageConst.SIGNUP_MAIL_SEND_ERROR);

	String messageId;
	
}
