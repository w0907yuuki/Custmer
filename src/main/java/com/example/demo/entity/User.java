package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;
import com.example.demo.entity.converter.UserStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
public class User {

	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="birthyear")
	private String birthyear;
	
	@Column(name="birthmon")
	private String birthmon;
	
	@Column(name="birthday")
	private String birthday;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="mailaddress")
	private String mailaddress;
	
	@Id
	@Column(name="userid")
	private String userid;
	
	@Column(name="password")
	private String password;	
	
	/** ログイン失敗回数 */
	@Column(name = "login_failure_count")
	private int loginFailureCount = 0;

	/** アカウントロック日時 */
	@Column(name = "account_locked_time")
	private LocalDateTime accountLockedTime;

	/** 利用可能か(true:利用可能) */
	@Column(name = "is_disabled")
	@Convert(converter = UserStatusConverter.class)
	private UserStatusKind userStatusKind;

	/** ユーザー権限種別 */
	@Convert(converter = UserAuthorityConverter.class)
	private AuthorityKind authorityKind;
	
	/** 本登録完了有無(仮登録状態ならfalse)*/
	@Column(name = "is_signup_completed")
	private boolean signupCompleted;
	
	/** 登録日時 */
	@Column(name = "create_time")
	private LocalDateTime createTime;

	/** 最終更新日時 */
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	/** 最終更新ユーザ */
	@Column(name = "update_user")
	private String updateUser;
	
	/** ワンタイムコード */
	@Column(name = "one_time_code")
	private String oneTimeCode;

	/** ワンタイムコード有効期限 */
	@Column(name = "one_time_code_send_time")
	private LocalDateTime oneTimeCodeSendTime;
	
	public User() {
	}
	
	/**
	 * ログイン失敗回数をインクリメントする
	 * 
	 * @return ログイン失敗回数がインクリメントされたUserInfo
	 */
	public User incrementLoginFailureCount() {
		return new User(firstname,lastname,birthyear,birthmon,birthday,gender,mailaddress,userid, password, ++loginFailureCount, accountLockedTime, userStatusKind,authorityKind,signupCompleted,createTime,updateTime,updateUser,oneTimeCode,oneTimeCodeSendTime);
	}

	/**
	 * ログイン失敗情報をリセットする
	 * 
	 * @return ログイン失敗情報がリセットされたUserInfo
	 */
	public User resetLoginFailureInfo() {
		return new User(firstname,lastname,birthyear,birthmon,birthday,gender,mailaddress,userid, password, 0, null, userStatusKind,authorityKind,signupCompleted,createTime,updateTime,updateUser,oneTimeCode,oneTimeCodeSendTime);
	}

	/**
	 * アカウントロック状態に更新する
	 * 
	 * @return ログイン失敗階位数、アカウントロック日時が更新されたUserInfo
	 */
	public User updateAccountLocked() {
		return new User(firstname,lastname,birthyear,birthmon,birthday,gender,mailaddress,userid, password, 0, LocalDateTime.now(), userStatusKind,authorityKind,signupCompleted,createTime,updateTime,updateUser,oneTimeCode,oneTimeCodeSendTime);
	}
	
}
