package com.bear.blockchain.dto;

import java.util.Date;
import java.util.List;

public class TransactionInBlockDto {

    private String txid;

    private String txhash;

    private String weight;

    private Long size;

    private Date time;

    private Double input;

    private Double output;

    private List<TxDetailTxInfo> txDetailTxInfo;

    public List<TxDetailTxInfo> getTxDetailTxInfo() {
        return txDetailTxInfo;
    }

    public void setTxDetailTxInfo(List<TxDetailTxInfo> txDetailTxInfo) {
        this.txDetailTxInfo = txDetailTxInfo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Double getInput() {
        return input;
    }

    public void setInput(Double input) {
        this.input = input;
    }

    public Double getOutput() {
        return output;
    }

    public void setOutput(Double output) {
        this.output = output;
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
