package com.mbb.transaction;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Version // add this annotation for optimistic locking
//    private Long version;
    private String accountNumber;
    private Double trxAmount;
    private String description;
    private String trxDate;
    private String trxTime;
    private String customerId;
}
