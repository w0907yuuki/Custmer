package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//private finalに対してコンストラクタを入れる
public class LoginService {

	private final UserRepository repository;
	
	public Optional<User>serchUserById(String userid){
		return repository.findById(userid);
	}
}
