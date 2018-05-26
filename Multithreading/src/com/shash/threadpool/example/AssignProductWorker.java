package com.shash.threadpool.example;

public class AssignProductWorker implements Runnable{

	private UserRequest ur;
	
	public AssignProductWorker(UserRequest ur) {
		this.ur = ur;
	}
	@Override
	public void run() {
		
		ProductRepository.getInstance().assignPhone(ur);
		
	}

}
