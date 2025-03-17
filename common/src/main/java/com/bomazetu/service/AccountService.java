package com.bomazetu.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bomazetu.model.Account;


public interface AccountService {
	
	public Account save(Account account);
	public Account update(Account account);
	public Account findById(Long Id);
	public String delete(Long Id);
	public List<Account> findAll();

}
