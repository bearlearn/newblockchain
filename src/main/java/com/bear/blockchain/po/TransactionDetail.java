package com.bear.blockchain.po;

public class TransactionDetail {
    private Long txDetailId;

    private String txHash;

    private String address;

    private Byte type;

    private Double amount;

    public Long getTxDetailId() {
        return txDetailId;
    }

    public void setTxDetailId(Long txDetailId) {
        this.txDetailId = txDetailId;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash == null ? null : txHash.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}