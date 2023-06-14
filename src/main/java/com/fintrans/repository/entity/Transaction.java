package com.fintrans.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {
	@Id
	@Column(name = "TRANSACTION_ID")
    private Integer transactionId;
	@Column(name = "TYPE")
    private String type;
	@Column(name = "CURRENCY")
    private String currency;
	@Column(name = "AMOUNT")
    private Double amount;
	@Column(name = "PARENT_ID")
    private Integer parentId;

}
