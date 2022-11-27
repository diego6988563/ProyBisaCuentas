package com.demo.bancobisa.services;

import com.demo.bancobisa.api.*;
import com.demo.bancobisa.config.BaseResponse;
import com.demo.bancobisa.config.CustomException;
import com.demo.bancobisa.entities.AccountEntity;
import com.demo.bancobisa.entities.TransactionEntity;
import com.demo.bancobisa.repository.AccountRepository;
import com.demo.bancobisa.repository.TransactionRepository;
import com.demo.bancobisa.services.data.Account;
import com.demo.bancobisa.services.data.Transaction;
import com.demo.bancobisa.services.mapper.AccountMapper;
import com.demo.bancobisa.services.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class AccountService implements AccountApi {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final String HOLD = "HOLD";
    private static final String ACTIVE = "ACTIVE";

    @Override
    public ResponseEntity<BaseResponse<BalanceResponse>> balance(BalanceRequest request) throws CustomException {
        return ok(new BaseResponse<BalanceResponse>().setResult(
                accountRepository.findByAccountNumber(request.getUserCode(), request.getAccountNumber())
                        .map(mapper -> AccountMapper.mapperToAccount(mapper))
                        .map(mapper -> new BalanceResponse()
                                .setBalance(BigDecimal.valueOf(mapper.getBalance()))
                                .setCurrency(mapper.getCurrency()==0?"Bs":mapper.getCurrency()==2?"USD":"UNKOWN")
                                .setLastUpdated(mapper.getLastUpdate())
                        )
                        .orElseThrow(() -> new CustomException("Cuenta no encontrada", HttpStatus.BAD_REQUEST))
        ));
    }

    @Override
    public ResponseEntity<BaseResponse<CreditAmountResponse>> creditAmount(CreditAmountRequest request) throws CustomException {

        Account account = accountRepository.findByAccountNumber(request.getUserCode(), request.getAccountNumber())
                .filter(predicate -> predicate.getState().contentEquals("HOLDED"))
                .map(mapper -> AccountMapper.mapperToAccount(mapper))
                .orElseThrow(() -> new CustomException("Cuenta no encontrada", HttpStatus.BAD_REQUEST));

        // Validaciones de cuenta
        //if (request.getCurrency()!=account.getCurrency())
            //throw new CustomException("La moneda no es la misma de la cuenta", HttpStatus.BAD_REQUEST);

        // Calculo de balance a debitar
        Double balance = account.getBalance() + request.getAmount();

        // Actualizando registro
        AccountEntity accountUpdated = AccountMapper.mapperToAccountEntity(account);
        AccountMapper.mapperToAccountEntity(new Account()
                .setId(null)
                .setBalance(balance)
                .setState(balance<=0?HOLD:ACTIVE), accountUpdated);
        Account updated = Optional.ofNullable(accountRepository.save(accountUpdated))
                .map(mapper -> AccountMapper.mapperToAccount(mapper))
                .orElseThrow(() -> new CustomException("Cuenta no registrada", HttpStatus.BAD_REQUEST));

        // Graba en tabla de historial
        Transaction historial = Optional.ofNullable(transactionRepository.save(new TransactionEntity()
                        .setAccountNumber(request.getAccountNumber())
                        .setOperation("CREDIT")
                        .setExecuteDate(new Date())
                ))
                .map(mapper -> TransactionMapper.mapperToTransaction(mapper))
                .orElseThrow(() -> new CustomException("Error al almacenar historial de transaccion", HttpStatus.BAD_REQUEST));

        return ok(new BaseResponse<CreditAmountResponse>().setResult(new CreditAmountResponse()
                .setAccount(updated)
        ));
    }

    @Override
    public ResponseEntity<BaseResponse<DebitAmountResponse>> debitAmount(DebitAmountRequest request) throws CustomException {

        Account account = accountRepository.findByAccountNumber(request.getUserCode(), request.getAccountNumber())
                //.filter(predicate -> !predicate.getState().contentEquals("HOLDED"))
                .map(mapper -> AccountMapper.mapperToAccount(mapper))
                .orElseThrow(() -> new CustomException("Cuenta no encontrada", HttpStatus.BAD_REQUEST));

        // Validaciones de cuenta
        if (account.getState().contentEquals(HOLD))
            throw new CustomException("Cuenta en estado: " + HOLD, HttpStatus.BAD_REQUEST);

        //if (request.getCurrency()!=account.getCurrency())
            //throw new CustomException("La moneda no es la misma de la cuenta", HttpStatus.BAD_REQUEST);

        // Calculo de balance a debitar
        Double balance = account.getBalance() - request.getAmount();

        // Actualizando registro
        AccountEntity accountUpdated = AccountMapper.mapperToAccountEntity(account);
        AccountMapper.mapperToAccountEntity(new Account()
                .setId(null)
                .setBalance(balance)
                .setState(balance<=0?HOLD:ACTIVE), accountUpdated);
        Account updated = Optional.ofNullable(accountRepository.save(accountUpdated))
                .map(mapper -> AccountMapper.mapperToAccount(mapper))
                .orElseThrow(() -> new CustomException("Cuenta no registrada", HttpStatus.BAD_REQUEST));

        // Graba en tabla de historial
        Transaction historial = Optional.ofNullable(transactionRepository.save(new TransactionEntity()
                        .setAccountNumber(request.getAccountNumber())
                        .setOperation("DEBIT")
                        .setExecuteDate(new Date())
                ))
                .map(mapper -> TransactionMapper.mapperToTransaction(mapper))
                .orElseThrow(() -> new CustomException("Error al almacenar historial de transaccion", HttpStatus.BAD_REQUEST));

        // retornando respuesta
        return ok(new BaseResponse<DebitAmountResponse>().setResult(new DebitAmountResponse()
                .setAccount(updated)
        ));
    }

    @Override
    @Transactional(rollbackOn = { CustomException.class })
    public ResponseEntity<BaseResponse<RegisterAccountResponse>> registerAccount(RegisterAccountRequest request) throws CustomException {

        // Efectuando registro de informacion de cuenta
        RegisterAccountResponse response = Optional.ofNullable(accountRepository.save(AccountMapper.mapperToAccountEntity(request)))
                .map(mapper -> new RegisterAccountResponse()
                        .setAccount(AccountMapper.mapperToAccount(mapper))
                )
                .orElseThrow(() -> new CustomException("Cuenta no registrada", HttpStatus.BAD_REQUEST));

        // Graba en tabla de historial
        Transaction historial = Optional.ofNullable(transactionRepository.save(new TransactionEntity()
                        .setAccountNumber(request.getAccountNumber())
                        .setOperation("REGISTER")
                        .setExecuteDate(new Date())
                ))
                .map(mapper -> TransactionMapper.mapperToTransaction(mapper))
                .orElseThrow(() -> new CustomException("Error al almacenar historial de transaccion", HttpStatus.BAD_REQUEST));

        // retornando respuesta
        return ok(new BaseResponse<RegisterAccountResponse>().setResult(response));
    }

    @Override
    public ResponseEntity<BaseResponse<TransactionHistoryResponse>> transactionHistory(TransactionHistoryRequest request) throws CustomException {
        return ok(new BaseResponse<TransactionHistoryResponse>().setResult(new TransactionHistoryResponse()
                .setTransactions(TransactionMapper
                        .mapperToTransactions(transactionRepository.findByAccountNumber(request.getAccountNumber())))
        ));
    }
}
