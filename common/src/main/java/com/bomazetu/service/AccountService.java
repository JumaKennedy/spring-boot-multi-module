package com.bomazetu.service;

import java.util.List;

import com.bomazetu.dto.AccountDTO;
import com.bomazetu.model.Account;


public interface AccountService {
	
	public Account save(Account account);
	public Account update(Account account);
	public AccountDTO findById(Long Id);
	public String delete(Long Id);
	public List<Account> findAll();

}
