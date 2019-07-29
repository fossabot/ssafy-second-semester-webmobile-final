package com.ssafy.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

/**
 * Security 기본 User 클래스를 상속받아 커스터마이징 (로그인 처리)
 * */
@Getter @Setter
public class SecurityAccount extends User {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;

	public SecurityAccount(Account account) {
		super(account.getAccount_email(), account.getAccount_password(),makeGrantedAuthority(account.getAccount_role_name()));
	}

	private static List<GrantedAuthority> makeGrantedAuthority(String role) {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
		return list;
	}

}
