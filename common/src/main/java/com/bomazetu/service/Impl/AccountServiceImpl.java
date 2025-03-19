package com.bomazetu.service.Impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bomazetu.dao.AccountsRepository;
import com.bomazetu.dto.AccountDTO;
import com.bomazetu.model.Account;
import com.bomazetu.service.AccountService;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountsRepository accountsRepository;	
	
	@Autowired
    private ModelMapper modelMapper;

	@Override
	public Account save(Account account) {
		
		Account accountSavedToDB = this.accountsRepository.save(account);
        
		return accountSavedToDB;
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
	public Optional<Account> findById(Long Id) {
		return accountsRepository.findById(Id);
	}

	@Override
	public String delete(Long Id) {
		
		try {
			accountsRepository.deleteById(Id);
			return "Delete Success";
		} catch (Exception e) {			
			e.printStackTrace();
			return "Entity does not exist";
		}
			
	}

	@Override
	public List<Account> findAll() {		
		return accountsRepository.findAll();
	}
	
	AccountDTO accountDTO(Account account) {		
		return this.modelMapper.map(account, AccountDTO.class); 
	}

	@Override
	public List<Account> findByLastName(String name) {
		
		return accountsRepository.findBylname(name);
	}

}
