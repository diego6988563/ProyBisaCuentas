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
@Table(name = "TRANSACTION")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "OPERATION", length = 50)
    private String operation;
    @Basic
    @Column(name = "ACCOUNT_NUMBER", length = 250)
    private String accountNumber;
    @Basic
    @Column(name = "EXECUTE_DATE", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date executeDate;

}
