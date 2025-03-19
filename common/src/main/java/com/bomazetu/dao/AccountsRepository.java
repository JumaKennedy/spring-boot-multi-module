package com.bomazetu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bomazetu.model.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

	
	List<Account> findBylname(String lname);

}
