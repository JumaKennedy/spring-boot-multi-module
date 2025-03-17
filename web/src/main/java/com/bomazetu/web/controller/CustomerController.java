package com.bomazetu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bomazetu.dto.AccountDTO;
import com.bomazetu.model.Account;
import com.bomazetu.service.AccountService;

/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping({"/home","/"})
    public ResponseEntity<String> userEndpoint() {
        
        return ResponseEntity.ok("Hello from customer api - USER");
    }
      
    @GetMapping("/accounts/findAll")
    public ResponseEntity<List<Account>> findAll() {     
    	
    	//init data
    	if(accountService.findAll().isEmpty()) {
    		Account account = new Account();
    		account.setAge(28);
    		account.setCity("Memphis");
    		account.setFname("Smith");
    		account.setLname("Amoth");
    		account.setGender("Male");
    		account.setPhone("903999399");
    		account.setState("TN");
    		account.setStreet("Spring River");
    		accountService.save(account);
    	}    	
        
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }    
    
    @PostMapping(value = "/accounts/save")
    public ResponseEntity<Account> save(@RequestBody Account account) {
    	Account save =accountService.save(account);
        return new ResponseEntity<Account>(save, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
    	AccountDTO userDto = this.accountService.findById(id);
	    return new ResponseEntity<AccountDTO>(userDto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<Account> updateUser(@PathVariable Long id, @RequestBody Account account) {
	    Account update = accountService.update(account);
	    return new ResponseEntity<Account>(update, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = accountService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
   
}
