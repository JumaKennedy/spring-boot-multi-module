package com.bomazetu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="account")
public class Account{		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull @NotBlank(message = "First Name is required")
	//@Size(min = 2, max = 100, message = "First Name must be between 2 and 100 characters")	  
	private String fname;
	
	@NotNull 
	@NotBlank(message = "Last Name is required")
	//@Size(min = 2, max = 100, message = "Last Name must be between 2 and 100 characters")
	private String lname;
	
	@NotBlank(message = "Phone is required")
	//@Size(min = 7, max = 14, message = "Phone must be between 7 and 14 characters")
	private String phone;
	
	@NotBlank(message = "State Name is required")
	//@Size(min = 2, max = 30, message = "State must be between 2 and 30 characters")
	private String state;
	
	@NotBlank(message = "City is required")
	//@Size(message = "City is required")
	private String city;
	
	@NotBlank(message = "Address is required")
	//@Size(min = 2, max = 100, message = "Address must be between 2 and 100 characters")
	private String street;
	
	@Size(min = 18, max = 100, message = "Age must be between 18 and 120 characters")
	private int age;
	
	@NotBlank(message = "Gender is required")
	//@Size(min = 2, max = 10, message = "Name must be between 4 and 10 characters")
	private String gender;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
