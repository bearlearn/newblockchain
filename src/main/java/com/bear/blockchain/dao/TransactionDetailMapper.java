package com.bear.blockchain.dao;

import com.bear.blockchain.dto.TransactionInfoDto;
import com.bear.blockchain.po.TransactionDetail;

public interface TransactionDetailMapper {
    int deleteByPrimaryKey(Long txDetailId);

    int insert(TransactionDetail record);

    int insertSelective(TransactionDetail record);

    TransactionDetail selectByPrimaryKey(Long txDetailId);

    int updateByPrimaryKeySelective(TransactionDetail record);

    int updateByPrimaryKey(TransactionDetail record);

    TransactionInfoDto getTransactionInfoByTxhash(String txHash);
}