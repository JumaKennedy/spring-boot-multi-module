package com.bomazetu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bomazetu.dao.AccountsRepository;
import com.bomazetu.model.Account;
import com.bomazetu.service.Impl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	  @InjectMocks
	  AccountServiceImpl service;

	  @Mock
	  AccountsRepository dao;
	
	  @Test
	  void testCreateOrSaveEmployee() {
		  
		  Account account = new Account("John", "Pombe", "9028839987", "TN", "Memphis", "290 Spring Cove", 34, "Male");
	      service.save(account);	         
	      verify(dao, times(1)).save(account);
	  }
	  
	  @Test
	  void testFindAllEmployees() {
	    List<Account> list = new ArrayList<Account>();
	    Account aOne = new Account("John", "Pombe", "9028839987", "TN", "Memphis", "290 Spring Cove", 34, "Male");
	    Account aTwo = new Account("Alex", "Oloo", "9028839987", "TX", "Dallas", "290 Spring Cove", 34, "Male");
	    Account aThree = new Account("Steve", "Waugh","9028839987", "MO", "Ballwin", "290 Spring Cove", 34, "Male");
	   
	    list.add(aOne);
	    list.add(aTwo);
	    list.add(aThree);

	    when(dao.findAll()).thenReturn(list);

	    //test
	    List<Account> aList = service.findAll();

	    assertEquals(3, aList.size());
	    verify(dao, times(1)).findAll();
	  }
	
	
}
