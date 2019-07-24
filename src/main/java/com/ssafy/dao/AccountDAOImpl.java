package com.ssafy.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {
	private SqlSession session;

	@Autowired
	public AccountDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public boolean insertAccount(Account account) {
		try {
			session.insert("insertAccount", account);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Account selectAccountByAccountEmail(String account_email) {
		return session.selectOne("selectAccountByAccountEmail", account_email);
	}

}
