package com.bear.blockchain.dto;

import java.util.Date;
import java.util.List;

public class TransactionInfoDto {
    private String txId;

    private String txHash;

    private Long txSize;

    private Integer txWeight;

    private Date txTime;

    private Double totalInput;

    private Double totalOutput;

    private Double txFees;

    private List<TxDetailTxInfo> txDetails;
}
