package com.bear.blockchain.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bear.blockchain.api.BitApi;
import com.bear.blockchain.api.BitcoinClient;
import com.bear.blockchain.dao.TransactionDetailMapper;
import com.bear.blockchain.dto.TransactionInBlockDto;
import com.bear.blockchain.dto.TransactionInfoDto;
import com.bear.blockchain.dto.TransationListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@Component
@CrossOrigin
public class TransactionController {


    @Autowired
    private BitApi bitApi;

    @Autowired
    private BitcoinClient bitcoinClient;

    @Autowired
    private TransactionDetailMapper transactionDetailMapper;

    @GetMapping("getRecentTransactions")
    public List<TransationListDto> getRecentTransactions(
            ) throws Throwable {
        List<TransationListDto> transactions=new ArrayList<>();
        JSONArray getrawmempool = bitcoinClient.getrawmempool();
        for (int i=1;i<6;i++){
            String mempoolHash = getrawmempool.getString(getrawmempool.size() - i);

            JSONObject getmempoolentry = bitcoinClient.getmempoolentry(mempoolHash);

            Double fee = getmempoolentry.getDouble("fee");

            String wtxid = getmempoolentry.getString("wtxid");

            JSONObject transaction = bitcoinClient.getRawTransaxtion(mempoolHash);

            TransationListDto transationListDto = new TransationListDto();

            transationListDto.setTxId(wtxid);

            transationListDto.setTxHash(transaction.getString("hash"));

            transationListDto.setTxTime(getmempoolentry.getLong("time"));

            JSONArray vouts = transaction.getJSONArray("vout");

            Double amount=0.0;

            for (int v=0;v<vouts.size();v++){
                JSONObject vout = vouts.getJSONObject(v);
                Double value = vout.getDouble("value");
                amount+=value;
            }
            transationListDto.setAmount(amount);
            transactions.add(transationListDto);
        }
        return transactions;


    }

    @GetMapping("getRecentTransactionsByNamAndType")
    public List<TransationListDto> getRecentTransactionsByNamAndType(
            @RequestParam String name,
            @RequestParam String type

    ){
        return null;
    }

    @RequestMapping("getTransactionInfoById")
    public TransactionInfoDto getTransactionInfoById(
            @RequestParam String txId
    ){
     return null;
    }

    /**
     * 根据交易hash查询交易详情
     * @param txHash
     * @return
     */
    @RequestMapping("getTransactionInfoByTxhash")
    public TransactionInfoDto getTransactionInfoByTxhash(
            @RequestParam String txHash
    ) throws Throwable {
        transactionDetailMapper.getTransactionInfoByTxhash(txHash);
        return transactionDetailMapper.getTransactionInfoByTxhash(txHash);
    }


}
