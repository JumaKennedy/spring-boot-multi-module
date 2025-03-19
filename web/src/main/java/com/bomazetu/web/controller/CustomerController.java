package com.bomazetu.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.bomazetu.response.ResponseData;
import com.bomazetu.service.AccountService;

import jakarta.validation.Valid;

/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
	
	private AccountService accountService;
	
	private ModelMapper modelMapper;
	
	protected CustomerController(AccountService accountService, ModelMapper mapper) {
        this.accountService = accountService;
        this.modelMapper = mapper;
    }
	
	@GetMapping({"/home","/"})
    public ResponseEntity<String> userEndpoint() {        
        return ResponseEntity.ok("Hello from customer api - USER");
    }
	
    @GetMapping({"/accounts/findAll","/accounts"})
    public ResponseEntity<?> list() {
    	
        List<Account> list = accountService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
            	
        ResponseData<?> response = new ResponseData<>(list.size()+ " Records Found", list2Dto(list));
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/accounts/lastName/{name}")
    public ResponseEntity<?> byLName(@PathVariable(value="name") String name) {
    	
        List<Account> list = accountService.findByLastName(name);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
            	
        ResponseData<?> response = new ResponseData<>(list.size()+ " Records Found with last Name "+ name, list2Dto(list));
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
        
    @PostMapping(value = "/accounts/save")
    public ResponseEntity<?> save(@Valid @RequestBody Account account) {
        Account save =accountService.save(account);
    	
    	AccountDTO userDto = entity2Dto(save);    	
        ResponseData<?> response = new ResponseData<>("Record Created Successfully", userDto);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
       
    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
    	Account userDto = accountService.findById(id).get();
	    
    	return new ResponseEntity<AccountDTO>(entity2Dto(userDto), HttpStatus.OK);
    }
    
    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Account account) {
    	
    	Account update = accountService.update(account);
	    
        ResponseData<?> response = new ResponseData<>("Record Updated Successfully", entity2Dto(update));
        
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = accountService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    private AccountDTO entity2Dto(Account entity) {
        return modelMapper.map(entity, AccountDTO.class);
    }
    
    private List<AccountDTO> list2Dto(List<Account> listUsers) {
        return listUsers.stream().map(
                entity -> entity2Dto(entity))
                    .collect(Collectors.toList());
    }
   
}
