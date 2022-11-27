package com.demo.bancobisa.services.mapper;

import com.demo.bancobisa.api.RegisterAccountRequest;
import com.demo.bancobisa.entities.AccountEntity;
import com.demo.bancobisa.services.data.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

    public static void mapperToAccountEntity(RegisterAccountRequest from, AccountEntity to) {
        if (from.getUserCode() != null) to.setUserCode(from.getUserCode());
        if (from.getAccountNumber() != null) to.setAccountNumber(from.getAccountNumber());
        if (from.getAccountType() != null) to.setAccountType(from.getAccountType());
        if (from.getBalance() != null) to.setBalance(from.getBalance());
        if (from.getCurrency() != null) to.setCurrency(Double.valueOf(from.getCurrency().contentEquals("Bs")?0:2));

        to.setState("ACTIVE");
        //to.setLastUpdate(from.getLastUpdate());
    }

    public static AccountEntity mapperToAccountEntity(RegisterAccountRequest from) {
        AccountEntity to = new AccountEntity();
        mapperToAccountEntity(from, to);
        return to;
    }

    public static void mapperToAccountEntity(Account from, AccountEntity to) {
        if (from.getId() != null) to.setId(from.getId());
        if (from.getUserCode() != null) to.setUserCode(from.getUserCode());
        if (from.getAccountNumber() != null) to.setAccountNumber(from.getAccountNumber());
        if (from.getAccountType() != null) to.setAccountType(from.getAccountType());
        if (from.getBalance() != null) to.setBalance(from.getBalance());
        if (from.getCurrency() != null) to.setCurrency(from.getCurrency());
        if (from.getState() != null) to.setState(from.getState());
        if (from.getLastUpdate() != null) to.setLastUpdate(from.getLastUpdate());
    }

    public static AccountEntity mapperToAccountEntity(Account from) {
        AccountEntity to = new AccountEntity();
        mapperToAccountEntity(from, to);
        return to;
    }

    public static void mapperToAccount(AccountEntity from, Account to) {
        if (from.getId() != null) to.setId(from.getId());
        if (from.getUserCode() != null) to.setUserCode(from.getUserCode());
        if (from.getAccountNumber() != null) to.setAccountNumber(from.getAccountNumber());
        if (from.getAccountType() != null) to.setAccountType(from.getAccountType());
        if (from.getBalance() != null) to.setBalance(from.getBalance());
        if (from.getCurrency() != null) to.setCurrency(from.getCurrency());
        if (from.getState() != null) to.setState(from.getState());
        if (from.getLastUpdate() != null) to.setLastUpdate(from.getLastUpdate());
    }

    public static Account mapperToAccount(AccountEntity from) {
        Account to = new Account();
        mapperToAccount(from, to);
        return to;
    }

    public static List<AccountEntity> mapperToAccountEntities(List<Account> from) {
        return from.stream().map(mapper -> mapperToAccountEntity(mapper)).collect(Collectors.toList());
    }

    public static List<Account> mapperToAccounts(List<AccountEntity> from) {
        return from.stream().map(mapper -> mapperToAccount(mapper)).collect(Collectors.toList());
    }
}
