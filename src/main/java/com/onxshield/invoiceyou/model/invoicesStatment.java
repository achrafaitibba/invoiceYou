package com.onxshield.invoiceyou.model;

import java.util.Date;
import java.util.List;

public class invoicesStatment {

    private String invoiceId;
    private Date invoiceDAte;
    //todo this is just for the mapping, review the attributes to select what to send to the client side
    private client client;
    private Long totalTTC;
    private Long TVA;
    private paymentMethod paymentMethod;
    private int checkNumber;
    private Date paymentDate;
    private List<marchendise> marchendiseList;
    private Boolean printed;
    private action action;
    private status status;
    private String invoiceFile; //file could be word or pdf
}
