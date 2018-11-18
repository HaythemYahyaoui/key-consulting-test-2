package com.keyconsulting.withdraw.model;

import java.math.BigDecimal;

/**
 * Class to hold customer informations, it has a relation of composition with
 * the Account class, an account is mandatory for a Customer so it can't be
 * NULL, and if a customer is deleted then his account too.
 * 
 * @author Haythem
 *
 */
public class Client {

	/**
	 * The name of the customer
	 */
	private final String name;
	
	/**
	 * The account of the customer
	 */
	private final Account account;
	
	/**
	 * Create a client with his name
	 */
	public Client(String name, BigDecimal amount) {
		super();
		this.name = name;
		this.account = new Account(amount);
	}

	/**	
	 * Return the name of the customer
	 * 
	 * @return String name the name of the customer
	 */
	public String name() {
		return name;
	}
	
	public void withdraw(BigDecimal amount) {
		account.withdraw(amount);
	}
	
	public BigDecimal balance() {
		return account.balance();
	}

	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}

}
