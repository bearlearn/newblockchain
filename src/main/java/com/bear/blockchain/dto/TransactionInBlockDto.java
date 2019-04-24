package com.bear.blockchain.dto;

import java.util.Date;
import java.util.List;

public class TransactionInBlockDto {

    private String txid;

    private String txhash;

    private Long size;

    private Date time;

    private List<TxDetailTxInfo> txDetailTxInfo;

    public List<TxDetailTxInfo> getTxDetailTxInfo() {
        return txDetailTxInfo;
    }

    public void setTxDetailTxInfo(List<TxDetailTxInfo> txDetailTxInfo) {
        this.txDetailTxInfo = txDetailTxInfo;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
