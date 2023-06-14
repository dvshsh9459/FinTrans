package com.fintrans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fintrans.request.TransactionRequest;
import com.fintrans.response.CurrencySumResponse;
import com.fintrans.response.TransactionResponse;
import com.fintrans.service.TransactionService;

@RestController
@RequestMapping("/bookingservice")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transaction/{transactionId}")
	public ResponseEntity<String> addTransaction(@PathVariable Integer transactionId,
			@RequestBody TransactionRequest transactionRequest) {
		return transactionService.addTransaction(transactionId, transactionRequest);
	}

	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Integer transactionId) {
		return transactionService.getTransactionById(transactionId);
	}

	@GetMapping("/types/{type}")
	public ResponseEntity<List<Integer>> getTransactionsByType(@PathVariable String type) {
		return transactionService.getTransactionByType(type);
	}

	@GetMapping("/currencies")
	public ResponseEntity<List<String>> getTransactionsByCurrency(@RequestParam String currency) {
		return transactionService.getTransactionByCurrency(currency);
	}

	@GetMapping("/sum/{transactionId}")
	public ResponseEntity<List<CurrencySumResponse>> getTotalAmountForParent(@PathVariable Integer transactionId) {
		return transactionService.getTotalAmount(transactionId);
	}
}
