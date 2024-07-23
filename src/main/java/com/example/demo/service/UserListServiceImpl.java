package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

	/** ユーザー情報テーブルDAO */
	private final UserRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserList() {
		return toUserListInfos(repository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto) {
		return toUserListInfos(findUserInfoByParam(dto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDeleteResult deleteUserInfoById(String userid) {
		var userInfo = repository.findById(userid);
		if (userInfo.isEmpty()) {
			return UserDeleteResult.ERROR;
		}

		repository.deleteById(userid);

		return UserDeleteResult.SUCCEED;
	}

	/**
	 * ユーザー情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<User> findUserInfoByParam(UserSearchInfo dto) {
		var loginIdParam = AppUtil.addWildcard(dto.getUserid());

		if (dto.getUserStatusKind() != null && dto.getAuthorityKind() != null) {
			return repository.findByUseridLikeAndUserStatusKindAndAuthorityKind(loginIdParam,
					dto.getUserStatusKind(), dto.getAuthorityKind());
		} else if (dto.getUserStatusKind() != null) {
			return repository.findByUseridLikeAndUserStatusKind(loginIdParam, dto.getUserStatusKind());
		} else if (dto.getAuthorityKind() != null) {
			return repository.findByUseridLikeAndAuthorityKind(loginIdParam, dto.getAuthorityKind());
		} else {
			return repository.findByUseridLike(loginIdParam);
		}
	}

	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザ一覧情報DTOのList
	 */
	private List<UserListInfo> toUserListInfos(List<User> userInfos) {
		var userListInfos = new ArrayList<UserListInfo>();
		for (User userInfo : userInfos) {
			var userListInfo = mapper.map(userInfo, UserListInfo.class);
			userListInfo.setStatus(userInfo.getUserStatusKind().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthorityKind().getDisplayValue());
			userListInfos.add(userListInfo);
		}

		return userListInfos;

	}

}