package com.fintrans.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TransactionRequest {
    private String type;
    private String currency;
    private Double amount;
    private Integer parentId;

}
