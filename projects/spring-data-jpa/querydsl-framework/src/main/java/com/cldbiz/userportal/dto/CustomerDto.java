package com.cldbiz.userportal.dto;

import java.util.Arrays;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Sort;

import com.cldbiz.userportal.domain.Account;
import com.cldbiz.userportal.domain.Customer;

import lombok.Data;

public @Data class CustomerDto extends AbstractDto {
	@Max(255)
	private String company;
	
	@Max(255)
	private String department;

	@Max(255)
	private String jobTitle;
    
	@NotNull
	@Max(255)
	private String firstName;
	
	@NotNull
	@Max(255)
	private String lastName;
	
	@NotNull
	@Max(255)
	private String workEmail;
	
	@NotNull
	@Max(255)
	private String workPhone;
    
	@Max(255)
	private String facebookIdentifier;
	
	@Max(255)
	private String twitterIdentifier;
	
	@Max(255)
	private String linkedinIdentifier;
    
	private Boolean canContact;
    
	private AccountDto accountDto;
	
    public CustomerDto() {
    	super();

    	this.setSortOrders(Arrays.asList(new Sort.Order(Sort.Direction.ASC, "firstName"), new Sort.Order(Sort.Direction.ASC, "lastName"))); 
    }
    
    public CustomerDto(Customer customer) {
    	super(customer);
    	
    	this.setCompany(customer.getCompany());
    	this.setDepartment(customer.getDepartment());
    	this.setJobTitle(customer.getJobTitle());
    	this.setFirstName(customer.getFirstName());
    	this.setLastName(customer.getLastName());
    	this.setWorkEmail(customer.getWorkEmail());
    	this.setWorkPhone(customer.getWorkPhone());
    	this.setFacebookIdentifier(customer.getFacebookIdentifier());
    	this.setTwitterIdentifier(customer.getTwitterIdentifier());
    	this.setLinkedinIdentifier(customer.getLinkedinIdentifier());
    	this.setCanContact(customer.getCanContact());
    	
    	this.setSortOrders(Arrays.asList(new Sort.Order(Sort.Direction.ASC, "firstName"), new Sort.Order(Sort.Direction.ASC, "lastName"))); 
    }
}
