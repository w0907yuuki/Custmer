package com.example.demo.dto;

import lombok.Data;

/**
 * ユーザー登録用DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class SignupInfo {

	/** ログインID */
	private String userid;

	/** パスワード */
	private String password;

	/** メールアドレス */
	private String mailaddress;
	
	/** 姓 */
	private String firstname;

	/** 名前 */
	private String lastname;
	
	/** 誕生年　*/
	private String birthyear;
	
	/** 誕生月 */
	private String birthmon;
	
	/** 誕生日 */
	private String birthday;
	
	/** 性別 */
	private String gender;
	
}