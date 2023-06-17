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

/**
 *  This is a controller class for transaction*/
@RestController
@RequestMapping("/bookingservice")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

/**This method will insert/update data for transaction
 * @param transactionId
 * @param transactionRequest
 *
 * @return ResponseEntity<String>
* */
	@PutMapping("/transaction/{transactionId}")
	public ResponseEntity<String> addTransaction(@PathVariable Integer transactionId,
			@RequestBody TransactionRequest transactionRequest) {
		return transactionService.addTransaction(transactionId, transactionRequest);
	}


/**
 * This method will return the transaction record for given transactionId
 * @param transactionId
 *
 * @return ResponseEntity<TransactionResponse> */
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Integer transactionId) {
		return transactionService.getTransactionById(transactionId);
	}


	/**
	 * This method returns a JSON list of all transaction ids of the given type.
	 *
	 * @return ResponseEntity<List<Integer>>  */
	@GetMapping("/types/{type}")
	public ResponseEntity<List<Integer>> getTransactionsByType(@PathVariable String type) {
		return transactionService.getTransactionByType(type);
	}

	/**
	 * This method returns a JSON list with all used currencies in the existing transactions.
	 *
	 * @return ResponseEntity<Set<String>> */
	@GetMapping("/currencies")
	public ResponseEntity<Set<String>> getTransactionsByCurrency() {
		return transactionService.getTransactionByCurrency();
	}

	/**
	 * This method returns the sum of all linked transactions with the respective currency
	 * @param transactionId
	 *
	 * @return  ResponseEntity<List<CurrencySumResponse>> */
	@GetMapping("/sum/{transactionId}")
	public ResponseEntity<List<CurrencySumResponse>> getTotalAmountForParent(@PathVariable Integer transactionId) {
		return transactionService.getTotalAmount(transactionId);
	}
}
