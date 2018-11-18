package com.keyconsulting.withdraw.model;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
 	
/**
 * Class to manage account : this class is the mother class of the relation
 * between the Account and Customer classes, so it for this class to maintain
 * the main relation	
 * 
 * @author Haythem
 *
 */
public class Account {

	/**
	 * Account balance of money
	 */
	private BigDecimal balance;

	/**
	 * Lock for balance multi-thread management
	 */
	private final ReadWriteLock balanceLock = new ReentrantReadWriteLock();

	/**
	 * Create an account : generate an id and initiate the balance to 0.
	 */
	public Account() {
		super();
		balance = new BigDecimal(0);
	}

	/**
	 * Create an account with an amount of balance, it generate an id.
	 * 
	 * @param balance the initial balance in this account
	 * @throws IllegalArgumentException if the balance is null or its less then 0.
	 */
	public Account(BigDecimal balance) {
		if (balance == null) {
			throw new IllegalArgumentException("The balance must be not null");
		}
		if (balance.doubleValue() < 0) {
			throw new IllegalArgumentException("The account can not be created with negative balance");
		}
		this.balance = balance;
	}

	/**
	 * Return the amount of balance in this account, this method is safe for
	 * multi-threading cause BigDecimal is immutable.
	 * 
	 * @return BigDecimal balance;
	 */
	public BigDecimal balance() {
		return balance;
	}

	/**
	 * Withdraw an amount of money from the balance, this method is safe for
	 * multi-threading.
	 * 
	 * @param amount
	 * @throws IllegalArgumentException if ammount reference is null
	 */
	public void withdraw(BigDecimal amount) {
		if (amount == null) {
			throw new IllegalArgumentException("The account can not be null");
		}
		balanceLock.writeLock().lock();
		try {
			this.balance = this.balance.subtract(amount);
		} finally {
			balanceLock.writeLock().unlock();
		}
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}

}
