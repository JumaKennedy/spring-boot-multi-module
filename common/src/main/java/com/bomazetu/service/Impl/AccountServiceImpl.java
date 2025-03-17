package com.bomazetu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bomazetu.dao.AccountsRepository;
import com.bomazetu.model.Account;
import com.bomazetu.service.AccountService;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountsRepository accountsRepository;	

	@Override
	public Account save(Account account) {		
		return accountsRepository.save(account);
	}

	@Override
	@Transactional
	public Account update(Account account) {
		accountsRepository.findById(account.getId()).ifPresent(a->{
			a.setAge(account.getAge());
			a.setCity(account.getCity());
			a.setFname(account.getFname());
			a.setGender(account.getGender());
			a.setLname(account.getLname());
			a.setPhone(account.getPhone());
			a.setState(account.getState());
			a.setStreet(account.getStreet());
		});
		return account;
	}

	@Override
	public Account findById(Long Id) {		
		return accountsRepository.findById(Id).get();
	}

	@Override
	public String delete(Long Id) {
		try {
			accountsRepository.deleteById(Id);
		} catch (Exception e) {
			return "Delete error";
		}
		return "success";
	}

	@Override
	public List<Account> findAll() {		
		return accountsRepository.findAll();
	}

}
