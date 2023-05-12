package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Digits;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BidListId;

    @NotBlank
    private String account;

    private String type;

    @Digits(integer=14, fraction=2)
    private Double bidQuantity;

    @Digits(integer=14, fraction=2)
    private Double askQuantity;

    @Digits(integer=14, fraction=2)
    private Double bid;

    @Digits(integer=14, fraction=2)
    private Double ask;

    private String benchmark;

    private Timestamp bidListDate;

    private String commentary;

    private String security;

    private String status;

    private String trader;

    private String book;

    private String creationName;

    private Timestamp creationDate;

    private String revisionName;

    private Timestamp revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

}