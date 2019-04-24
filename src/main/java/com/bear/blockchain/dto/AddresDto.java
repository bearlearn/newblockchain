package com.bear.blockchain.dto;

public class AddresDto {
    private String addres;
    private String hash160;
    private Integer txSize;
    private Double receiveAmount;
    private Double finalBalance;

    public String getAddres() {

        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getHash160() {
        return hash160;
    }

    public void setHash160(String hash160) {
        this.hash160 = hash160;
    }

    public Integer getTxSize() {
        return txSize;
    }

    public void setTxSize(Integer txSize) {
        this.txSize = txSize;
    }

    public Double getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(Double receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }
}
