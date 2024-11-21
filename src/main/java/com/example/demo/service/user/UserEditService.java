package com.example.demo.service.user;

import java.util.Optional;

import com.example.demo.dto.user.UserEditResult;
import com.example.demo.dto.user.UserUpdateInfo;
import com.example.demo.entity.User;

/**
 * ユーザー編集画面Serviceインターフェース
 * 
 * @author ys-fj
 *
 */
public interface UserEditService {

	/**
	 * ログインIDを使ってユーザー情報テーブルを検索し、検索結果を返却します。
	 * 
	 * @param loginId ログインID
	 * @return 該当のユーザー情報テーブル登録情報
	 */
	Optional<User> searchUserInfo(String userid);

	/**
	 * ユーザー情報テーブルを更新します。
	 * 
	 * @param userUpdateInfo ユーザー更新情報
	 * @return 更新結果
	 */
	UserEditResult updateUserInfo(UserUpdateInfo userUpdateInfo);

}