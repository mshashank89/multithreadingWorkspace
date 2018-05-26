package com.shash.threads.g_deadlocks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.shash.threads.f_reentrant_locks.waitNotifyEquivalent.Account;

public class ResolveDeadLocksBankingSimulator {
	
	/*
Simulates random transfers of 1-10 rupees betwen Account1 to Account 2

The sum of the balances of Account1 and Account2 will be < 200000 if locks are not used.

***********************************************************************
*   Locking the locks in the same order is very important  
*   Locking the locks in different orders may cause DEADLOCKS  *******
*   
*   In this example, deadlocks are resolved by using a method acquireLocks() wich will manage the order in 
    which locks are acquired by threads.
*   
*   See the comment in the secondThread() method.
***********************************************************************

	 */

	private Account account1 = new Account(100000);
	private Account account2 = new Account(100000);

	private Lock _lock1 = new ReentrantLock();
	private Lock _lock2 = new ReentrantLock();

	private Random random = new Random();
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
		
		while(true){			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}
			finally {
				if (gotFirstLock && gotSecondLock){
					return;
				}
				else if (gotFirstLock){
					firstLock.unlock();
				}else if (gotSecondLock){
					secondLock.unlock();
				}
			}

			Thread.sleep(1);			
		}
	}

	public void firstThread() throws InterruptedException {
		// perform random transfers of amount from acount1 to account2

		for (int i = 0; i < 1000; i++) {
			acquireLocks(_lock1, _lock2);

			try {
				Account.transfer(account1, account2, random.nextInt(10));
				Thread.sleep(random.nextInt(1));
			} finally {
				_lock1.unlock();
				_lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {
		// perform random transfers of amount from acount2 to account1

		for (int i = 0; i < 1000; i++) {
			acquireLocks(_lock2, _lock1);
			
//			_lock2.lock();
//			_lock1.lock();
// Locking in the reverse order will cause DeadLock	
// In order to avoid causing deadlock, ALWAYS LOCK THE LOCKS IN THE SAME ORDER.
//									   =======================================
			

			try {
				Account.transfer(account2, account1, random.nextInt(10));
				Thread.sleep(random.nextInt(1));
			} finally {
				_lock1.unlock();
				_lock2.unlock();
			}
		}
	}

	public void finalTotalBalance() {
		System.out.println("Account-1 Balance : " + account1.getBalance());
		System.out.println("Account-2 Balance : " + account2.getBalance());
		System.out.println("Final total balance : "
				+ (account1.getBalance() + account2.getBalance()));
	}
}
