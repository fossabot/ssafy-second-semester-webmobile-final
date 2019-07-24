package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.dao.AccountDAOImpl;
import com.ssafy.vo.Account;
import com.ssafy.vo.SecurityAccount;

/**
 * Spring Security 설정을 위한 페이지
 */
@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

	AccountDAOImpl accountDAO;

	@Autowired
	public AccountServiceImpl(AccountDAOImpl accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public boolean insertAccount(Account account) {
		return accountDAO.insertAccount(account);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(accountDAO.selectAccountByAccountEmail(username))
				.filter(account -> account != null).map(account -> new SecurityAccount(account)).get();
	}

	@Override
	public Account selectAccountByAccountEmail(String account_email) {
		return accountDAO.selectAccountByAccountEmail(account_email);
	}

}
