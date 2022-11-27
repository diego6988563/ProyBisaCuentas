package com.demo.bancobisa.controller;

import com.demo.bancobisa.api.*;
import com.demo.bancobisa.config.BaseResponse;
import com.demo.bancobisa.config.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Security Service", description = "Servicio que gestiona las consultas de transferencias entre cuentas")
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    @Autowired
    private AccountApi accountApi;

    @PostMapping("/balance")
    @Operation(summary = "Obtiene categoria y compañias (empresas).",
            description = "Obtiene el listado de categorias y compañias (empresas). En caso de ejecutarse correctamente el estado sera 200."
    )
    public ResponseEntity<BaseResponse<BalanceResponse>> balance(@RequestBody @Valid BalanceRequest request) throws CustomException {
        return accountApi.balance(request);
    }

    @PostMapping("/creditAmount")
    @Operation(summary = "Obtiene categoria y compañias (empresas).",
            description = "Obtiene el listado de categorias y compañias (empresas). En caso de ejecutarse correctamente el estado sera 200."
    )
    public ResponseEntity<BaseResponse<CreditAmountResponse>> creditAmount(@RequestBody @Valid CreditAmountRequest request) throws CustomException {
        return accountApi.creditAmount(request);
    }

    @PostMapping("/debitAmount")
    @Operation(summary = "Obtiene categoria y compañias (empresas).",
            description = "Obtiene el listado de categorias y compañias (empresas). En caso de ejecutarse correctamente el estado sera 200."
    )
    public ResponseEntity<BaseResponse<DebitAmountResponse>> debitAmount(@RequestBody @Valid DebitAmountRequest request) throws CustomException {
        return accountApi.debitAmount(request);
    }

    @PostMapping("/registerAccount")
    @Operation(summary = "Obtiene categoria y compañias (empresas).",
            description = "Obtiene el listado de categorias y compañias (empresas). En caso de ejecutarse correctamente el estado sera 200."
    )
    public ResponseEntity<BaseResponse<RegisterAccountResponse>> registerAccount(@RequestBody @Valid RegisterAccountRequest request) throws CustomException {
        return accountApi.registerAccount(request);
    }

    @PostMapping("/transactionHistory")
    @Operation(summary = "Obtiene categoria y compañias (empresas).",
            description = "Obtiene el listado de categorias y compañias (empresas). En caso de ejecutarse correctamente el estado sera 200."
    )
    public ResponseEntity<BaseResponse<TransactionHistoryResponse>> transactionHistory(@RequestBody @Valid TransactionHistoryRequest request) throws CustomException {
        return accountApi.transactionHistory(request);
    }

}
