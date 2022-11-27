package com.demo.bancobisa.repository;

import com.demo.bancobisa.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Serializable> {

    @Query("select u from AccountEntity u where u.userCode=:userCode and u.accountNumber=:accountNumber")
    public Optional<AccountEntity> findByAccountNumber(@Param("userCode") String usercode, @Param("accountNumber") String accountNumber);

}
