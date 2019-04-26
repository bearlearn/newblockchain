package com.bear.blockchain.dto;

import java.util.Date;

public class TransationListDto {
    private String txId;

    private String txHash;

    private Long txTime;

    private Double amount;


    public String getTxId() {

        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public Long getTxTime() {
        return txTime;
    }

    public void setTxTime(Long txTime) {
        this.txTime = txTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
