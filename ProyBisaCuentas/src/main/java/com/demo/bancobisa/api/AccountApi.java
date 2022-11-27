package com.demo.bancobisa.api;

import com.demo.bancobisa.config.BaseResponse;
import com.demo.bancobisa.config.CustomException;
import org.springframework.http.ResponseEntity;

public interface AccountApi {

    ResponseEntity<BaseResponse<BalanceResponse>> balance(BalanceRequest request) throws CustomException;
    ResponseEntity<BaseResponse<CreditAmountResponse>> creditAmount(CreditAmountRequest request) throws CustomException;
    ResponseEntity<BaseResponse<DebitAmountResponse>> debitAmount(DebitAmountRequest request) throws CustomException;
    ResponseEntity<BaseResponse<RegisterAccountResponse>> registerAccount(RegisterAccountRequest request) throws CustomException;
    ResponseEntity<BaseResponse<TransactionHistoryResponse>> transactionHistory(TransactionHistoryRequest request) throws CustomException;

}
