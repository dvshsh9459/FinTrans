package com.fintrans.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TransactionResponse {
    private Integer transactionId;
    private String type;
    private String currency;
    private Double amount;
    private Integer parentId;

}
