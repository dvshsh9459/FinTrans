package com.fintrans.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fintrans.repository.TransactionRepository;
import com.fintrans.repository.entity.Transaction;
import com.fintrans.request.TransactionRequest;
import com.fintrans.response.CurrencySumResponse;
import com.fintrans.response.TransactionResponse;

/**
 *  This is a service class for transaction*/
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;


	/**This method will insert/update data for transaction
	 * @param transactionId
	 * @param transactionRequest
	 *
	 * @return ResponseEntity<String>
	 * */
	public ResponseEntity<String> addTransaction(Integer transactionId, TransactionRequest transactionRequest) {
		HttpStatus httpStatusCode =  HttpStatus.OK;
		String responseMessage = "OK";
		
		Transaction transaction = buildDataForDb(transactionId, transactionRequest);
		
		transactionRepository.save(transaction);
		
		return new ResponseEntity<>(responseMessage, httpStatusCode);
	}

	/**This method will create entity object from request
	 * @param transactionId
	 * @param transactionRequest
	 *
	 * @return Transaction
	 * */
	private Transaction buildDataForDb(Integer transactionId, TransactionRequest transactionRequest) {
		return Transaction.builder().transactionId(transactionId)
				.parentId(transactionRequest.getParentId())
				.amount(transactionRequest.getAmount())
				.currency(transactionRequest.getCurrency().toUpperCase())
				.type(transactionRequest.getType().toUpperCase()).build();
	}


	/**
	 * This method will return the transaction record for given transactionId
	 * @param transactionId
	 *
	 * @return ResponseEntity<TransactionResponse> */
	public ResponseEntity<TransactionResponse> getTransactionById(Integer transactionId) {
		Transaction transactionFromDb = transactionRepository.getReferenceById(transactionId);
		TransactionResponse transactionResponse= getTransactionResponse(transactionFromDb);
		return ResponseEntity.ok().body(transactionResponse);
	}


	/**
	 * This method will return the transaction response for given transaction From Db
	 * @param transactionFromDb
	 *
	 * @return TransactionResponse */
	private TransactionResponse getTransactionResponse(Transaction transactionFromDb) {
		return TransactionResponse.builder().transactionId(transactionFromDb.getTransactionId())
				.parentId(transactionFromDb.getParentId())
				.amount(transactionFromDb.getAmount())
				.currency(transactionFromDb.getCurrency())
				.type(transactionFromDb.getType()).build();
	}

	/**
	 * This method returns a JSON list of all transaction ids of the given type.
	 *
	 * @return ResponseEntity<List<Integer>>  */
	public ResponseEntity<List<Integer>> getTransactionByType(String type) {
		List<Integer> typeList =transactionRepository.findAll().stream()
				.filter(transaction -> transaction.getType().equalsIgnoreCase(type))
				.map(Transaction :: getTransactionId)
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(typeList);
	}

	/**
	 * This method returns a JSON list with all used currencies in the existing transactions.
	 *
	 * @return ResponseEntity<Set<String>> */
	public ResponseEntity<Set<String>> getTransactionByCurrency() {
		List<Transaction> allTransactions = transactionRepository.findAll();
		Set<String> currencyList =  allTransactions.stream()
				.map(Transaction::getCurrency)
				.collect(Collectors.toSet());
		return ResponseEntity.ok().body(currencyList);
	}

	/**
	 * This method returns the sum of all linked transactions with the respective currency
	 * @param transactionId
	 *
	 * @return  ResponseEntity<List<CurrencySumResponse>> */
	public ResponseEntity<List<CurrencySumResponse>> getTotalAmount(Integer transactionId) {
		Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
		List<CurrencySumResponse> currencySumResponseList = new ArrayList<>();
		if (optionalTransaction.isPresent()) {
			Transaction transaction = optionalTransaction.get();
			String currencyType = transaction.getCurrency();

			double sum = transactionRepository.findAll()
					.stream()
					.filter(t -> t.getCurrency().equals(currencyType))
					.mapToDouble(Transaction::getAmount)
					.sum();

			CurrencySumResponse currencySumResponse = new CurrencySumResponse();
			currencySumResponse.setCurrency(currencyType);
			currencySumResponse.setSum(sum);

			currencySumResponseList.add(currencySumResponse);

		}

		// throw new TransactionNotFoundException("Transaction not found with ID: " + transaction_id);
		return ResponseEntity.ok().body(currencySumResponseList);
	}
	
}

