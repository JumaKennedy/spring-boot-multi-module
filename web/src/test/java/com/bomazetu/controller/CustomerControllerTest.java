package com.bomazetu.controller;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.bomazetu.dao.AccountsRepository;
import com.bomazetu.model.Account;
import com.bomazetu.web.controller.CustomerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@WebMvcTest(CustomerController.class)
@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {
	
	  @MockitoBean
	  private AccountsRepository dao;

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;
	  
	  @Test
	  void shouldCreateAccount() throws Exception {
	    Account account = new Account("John", "Pombe", "9028839987", "TN", "Memphis", "290 Spring Cove", 34, "Male");
	    mockMvc.perform(post("/api/v1/customer/accounts/save").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(account)))
	        .andExpect(status().isCreated())
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldCreateTutorial() throws Exception {
		  Account account = new Account("John", "Pombe", "9028839987", "TN", "Memphis", "290 Spring Cove", 34, "Male");

	    mockMvc.perform(post("/api/v1/customer/accounts/findAll").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(account)))
	        .andExpect(status().isCreated())
	        .andDo(print());
	  }
	  
	  @Test
	  void shouldReturnNotFoundAccount() throws Exception {
	    long id = 1L;

	    when(dao.findById(id)).thenReturn(Optional.empty());
	    mockMvc.perform(get("/api/v1/customer/accounts/{id}", id))
	         .andExpect(status().isNotFound())
	         .andDo(print());
	  }
	  
	  @Test
	  void shouldDeleteAccount() throws Exception {
	    long id = 1L;

	    doNothing().when(dao).deleteById(id);
	    mockMvc.perform(delete("/api/v1/customer/accounts/{id}", id))
	         .andExpect(status().isNoContent())
	         .andDo(print());
	  }
	  
	  @Test
	  void shouldReturnListOfTutorialsWithFilter() throws Exception {
	    List<Account> accounts = new ArrayList<>(
	        Arrays.asList(new Account("John", "Pombe", "9028839987", "TN", "Memphis", "290 Spring Cove", 34, "Male"),
	        		new Account("Alex", "Oloo", "9028839987", "TX", "Dallas", "290 Spring Cove", 34, "Male")));

	    String name = "John";
	    MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
	    paramsMap.add("John", name);

	    when(dao.findBylname(name)).thenReturn(accounts);
	    mockMvc.perform(get("/api/tutorials").params(paramsMap))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(accounts.size()))
	        .andDo(print());
	  }
	
}
