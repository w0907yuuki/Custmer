package com.example.demo.form;

import lombok.Data;

/*ユーザーリスト表示画面Formクラス*/
@Data
public class CustomerForm {

	//顧客ID
	private String id;
	//顧客名
	private String name;
	//取引状態
	private boolean state; 
	//選択した顧客ID
	private Long selectedcustomerid;
	//選択した顧客IDのクリア
	public CustomerForm clearSelecteduserid() {
		this.selectedcustomerid = null;

		return this;
	}

}
