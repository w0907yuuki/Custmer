package com.example.demo.constant;

/**
 * URL定数クラス
 * 
 * @author ys-fj
 *
 */
public class UrlConst {

	/** ログイン画面 */
	public static final String LOGIN = "/login";

	/** ユーザー登録画面 */
	public static final String SIGNUP = "/signup";
	
	/** ユーザー登録情報確認画面 */
	public static final String SIGNUP_CONFIRM = "/signupConfirm";

	/** ユーザー登録情報確認結果画面 */
	public static final String SIGNUP_COMPLETION = "/signupCompletion";

	/** メニュー画面 */
	public static final String MENU = "/topmenu";

	/** ユーザー一覧画面 */
	public static final String USER_LIST = "/userList";
	
	/** ユーザー編集画面 */
	public static final String USER_EDIT = "/userEdit";
	
	public static final String CUSTOMER_EDIT = "/customeredit";
	
	public static final String CUSTOMER_REGIST ="/registcustomer";
	
	public static final String CUSTOMER = "/customer";

	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, "/webjars/**","/css/**" };
}