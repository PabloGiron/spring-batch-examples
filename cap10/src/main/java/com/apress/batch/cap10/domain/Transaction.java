package com.apress.batch.cap10.domain;

import com.apress.batch.cap10.batch.JaxbDateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "transaction")
public class Transaction {
    private long transactionId;
    private long accountId;
    private String description;
    private BigDecimal credit;
    private BigDecimal debit;
    private Date timestamp;
    public Transaction() {
    }
    public Transaction(long transactionId,
                       long accountId,
                       String description,
                       BigDecimal credit,
                       BigDecimal debit,
                       Date timestamp) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.description = description;
        this.credit = credit;
        this.debit = debit;
        this.timestamp = timestamp;
    }
    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getAccountId() {
        return accountId;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTransactionAmount() {
        if(credit != null) {
            if(debit != null) {
                return credit.add(debit);
            }
            else {
                return credit;
            }
        }
        else if(debit != null) {
            return debit;
        }
        else {
            return new BigDecimal(0);
        }
    }
}
