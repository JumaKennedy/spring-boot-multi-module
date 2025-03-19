package com.bomazetu.service;

import java.util.List;
import java.util.Optional;

import com.bomazetu.model.Account;


public interface AccountService {
	
	public Account save(Account account);
	public Account update(Account account);
	public Optional<Account> findById(Long Id);
	public String delete(Long Id);
	public List<Account> findAll();
	public List<Account> findByLastName(String name);

}
