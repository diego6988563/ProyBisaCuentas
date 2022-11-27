package com.demo.bancobisa.repository;

import com.demo.bancobisa.entities.AccountEntity;
import com.demo.bancobisa.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Serializable> {

    @Query("select u from TransactionEntity u where u.accountNumber=:accountNumber")
    public List<TransactionEntity> findByAccountNumber(@Param("accountNumber") String accountNumber);

}
