package com.demo.bancobisa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "USER_CODE", length = 250)
    private String userCode;
    @Basic
    @Column(name = "ACCOUNT_NUMBER", unique = true, length = 250)
    private String accountNumber;
    @Basic
    @Column(name = "ACCOUNT_TYPE", length = 250)
    private String accountType;
    @Basic
    @Column(name = "BALANCE")
    private Double balance;
    @Basic
    @Column(name = "CURRENCY")
    private Double currency;
    @Basic
    @Column(name = "STATE", length = 50)
    private String state;
    @Basic
    @Column(name = "LAST_UPDATE", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

}
