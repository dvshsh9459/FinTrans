package com.fintrans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrans.repository.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
