package com.fintrans.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fintrans.request.TransactionRequest;
import com.fintrans.response.CurrencySumResponse;
import com.fintrans.response.TransactionResponse;
import com.fintrans.service.TransactionService;

@RestController
@RequestMapping("/bookingservice")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PutMapping("/transaction/{transactionId}")
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
	public ResponseEntity<Set<String>> getTransactionsByCurrency(@RequestParam String currency) {
		return transactionService.getTransactionByCurrency(currency);
	}

	@GetMapping("/sum/{transactionId}")
	public ResponseEntity<List<CurrencySumResponse>> getTotalAmountForParent(@PathVariable Integer transactionId) {
		return transactionService.getTotalAmount(transactionId);
	}
}
