package com.example.demo.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupForm {
	
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	@NotEmpty
	private String birthyear;
	@NotNull
	@NotEmpty
	private String birthmon;
	@NotNull
	@NotEmpty
	private String birthday;
	
	private String gender;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]*\\.)+[A-Za-z]{2,}$", message = "{signup.invalidMailAddress}")
	private String mailaddress;
	
	/** ログインID */
	@NotNull
	private String userid;

	/** パスワード */
	@NotNull
	private String password;

	
	private String checkpassword;
}
