package com.ssafy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account {
	int account_no;
	String account_email;
	String account_password;
	String account_name;
	String account_gitlab_email;
	String account_role_name;
	String account_register_date;

	public Account() {
	}

	public Account(int account_no, String account_email, String account_password, String account_name,
			String account_gitlab_email, String account_role_name, String account_register_date) {
		this.account_no = account_no;
		this.account_email = account_email;
		this.account_password = account_password;
		this.account_name = account_name;
		this.account_gitlab_email = account_gitlab_email;
		this.account_role_name = account_role_name;
		this.account_register_date = account_register_date;
	}

}
