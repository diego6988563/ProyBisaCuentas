package com.demo.bancobisa.services.mapper;

import com.demo.bancobisa.entities.TransactionEntity;
import com.demo.bancobisa.services.data.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static void mapperToTransactionEntity(Transaction from, TransactionEntity to) {
        if (from.getId() != null) to.setId(from.getId());
        if (from.getOperation() != null) to.setOperation(from.getOperation());
        if (from.getAccountNumber() != null) to.setAccountNumber(from.getAccountNumber());
        if (from.getExecuteDate() != null) to.setExecuteDate(from.getExecuteDate());
    }

    public static TransactionEntity mapperToTransactionEntity(Transaction from) {
        TransactionEntity to = new TransactionEntity();
        mapperToTransactionEntity(from, to);
        return to;
    }

    public static void mapperToTransaction(TransactionEntity from, Transaction to) {
        if (from.getId() != null) to.setId(from.getId());
        if (from.getOperation() != null) to.setOperation(from.getOperation());
        if (from.getAccountNumber() != null) to.setAccountNumber(from.getAccountNumber());
        if (from.getExecuteDate() != null) to.setExecuteDate(from.getExecuteDate());
    }

    public static Transaction mapperToTransaction(TransactionEntity from) {
        Transaction to = new Transaction();
        mapperToTransaction(from, to);
        return to;
    }

    public static List<TransactionEntity> mapperToTransactionEntities(List<Transaction> from) {
        return from.stream().map(mapper -> mapperToTransactionEntity(mapper)).collect(Collectors.toList());
    }

    public static List<Transaction> mapperToTransactions(List<TransactionEntity> from) {
        return from.stream().map(mapper -> mapperToTransaction(mapper)).collect(Collectors.toList());
    }
}
