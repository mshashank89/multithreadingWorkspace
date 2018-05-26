package com.shash.threads.f_reentrant_locks.waitNotifyEquivalent;

public class Account {
	
	private int balance;

	public Account(int initialBalance){
		this.balance = initialBalance;
	}
	
	public static void transfer(Account fromAccount, Account toAccount, int transferAmount){
		if (fromAccount.balance >= transferAmount){
			fromAccount.balance -= transferAmount;
			toAccount.balance += transferAmount;
		}
	}
	
	public int getBalance(){
		return balance;
	}

}
