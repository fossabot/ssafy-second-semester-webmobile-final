package com.ssafy.service;

import com.ssafy.vo.Account;

public interface AccountService {
	public boolean insertAccount(Account account);
	public Account selectAccountByAccountEmail(String account_email);
}
