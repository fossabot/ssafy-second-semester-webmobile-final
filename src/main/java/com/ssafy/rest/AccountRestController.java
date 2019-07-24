package com.ssafy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.AccountServiceImpl;
import com.ssafy.vo.Account;
import com.ssafy.vo.Article;

@RestController
@RequestMapping(value = "/api/bears/accounts")
public class AccountRestController {

	AccountServiceImpl accountService;

	@Autowired
	public AccountRestController(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	//-- 임시 axios
	@GetMapping(value ="/{account_email}")
	public ResponseEntity<Account> selectAccountByAccountEmail(@PathVariable("account_email") String account_email) {
		System.out.println(account_email);
		Account account = accountService.selectAccountByAccountEmail(account_email);
		System.out.println(account);
		return account != null ? new ResponseEntity<Account>(account, HttpStatus.OK)
				: new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<Boolean> insertAccount(@RequestBody Account account) {
		System.out.println(account);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		account.setAccount_password(passwordEncoder.encode(account.getAccount_password()));
		account.setAccount_role_name("BASIC");
		return accountService.insertAccount(account) ? new ResponseEntity<Boolean>(true, HttpStatus.CREATED)
				: new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	} // end of func(insertAccount)
}
