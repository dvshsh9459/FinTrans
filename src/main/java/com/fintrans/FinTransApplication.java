package com.fintrans;

import com.fintrans.repository.TransactionRepository;
import com.fintrans.repository.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinTransApplication {
	@Autowired
	TransactionRepository transactionRepository;


	public static void main(String[] args) {
		SpringApplication.run(FinTransApplication.class, args);
	}


	public void run(String... args) throws Exception {
		Transaction one = new Transaction();
		one.setAmount(300.50);
		one.setCurrency("USD");
		one.setParentId(4);
		one.setType("CASH");
		transactionRepository.save(one);
		Transaction two = new Transaction();
		two.setAmount(300.50);
		two.setCurrency("INR");
		two.setParentId(4);
		two.setType("CASH");
		transactionRepository.save(two);
		Transaction three=  new Transaction();
		three.setAmount(200.50);
		three.setCurrency("USD");
		three.setParentId(2);
		three.setType("Credit");
		transactionRepository.save(three);
		Transaction four = new Transaction();
		four.setAmount(400.50);
		four.setCurrency("EUR");
		four.setParentId(2);
		four.setType("Credit");
		transactionRepository.save(four);

	}

}
