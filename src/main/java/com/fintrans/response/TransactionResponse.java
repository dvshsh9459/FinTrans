package com.fintrans.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


/**
 *  This is a class for response object for transaction*/
@Data
@Builder
@ToString
public class
TransactionResponse {
    private Integer transactionId;
    private String type;
    private String currency;
    private Double amount;
    private Integer parentId;

}
