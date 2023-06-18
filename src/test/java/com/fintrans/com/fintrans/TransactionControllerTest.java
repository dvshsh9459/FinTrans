package com.fintrans.com.fintrans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintrans.controller.TransactionController;
import com.fintrans.repository.TransactionRepository;
import com.fintrans.repository.entity.Transaction;
import com.fintrans.request.TransactionRequest;
import com.fintrans.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    TransactionService transactionService;

    @MockBean
    TransactionRepository transactionRepository;
    

    TransactionRequest transactionRequest1 = new TransactionRequest("expense","USD",10001.45,101);
    TransactionRequest transactionRequest2 = new TransactionRequest("expense","USD",20001.45,102);
    TransactionRequest transactionRequest3 = new TransactionRequest("expense","EURO",30001.45,103);
    TransactionRequest transactionRequest4 = new TransactionRequest("expense","JPN",40001.45,104);


    @Test
    public void createRecordSuccess() throws Exception {
        Transaction transaction = Transaction.builder()
                .transactionId(1)
                .type("expense")
                .currency("USD")
                .amount(10001.45)
                .parentId(101)
                .build();

        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/bookingservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(transaction));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void getTransactionRecord() throws Exception {

        Mockito.when(transactionRepository.findById(1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bookingservice/transaction/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
