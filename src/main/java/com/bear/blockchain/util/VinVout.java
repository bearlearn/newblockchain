package com.bear.blockchain.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bear.blockchain.api.BitcoinClient;
import com.bear.blockchain.dto.TransactionInBlockDto;
import com.bear.blockchain.dto.TxDetailTxInfo;
import com.bear.blockchain.enums.TransactionEnums;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * bear4_28
 */
@Component
public class VinVout {

    private Double outputTotal=0.0;

    private Double inputTotal=0.0;

    public void  zero(){
        this.inputTotal=0.0;
        this.outputTotal=0.0;
    }

    public TransactionInBlockDto vin(JSONObject tx, Date time, BitcoinClient bitcoinClient) throws Throwable {
        zero();
        TransactionInBlockDto transactionInBlockDto = new TransactionInBlockDto();
        String txid = tx.getString("txid");
        transactionInBlockDto.setTxid(txid);
        transactionInBlockDto.setTxhash(tx.getString("hash"));
        transactionInBlockDto.setTime(time);
        transactionInBlockDto.setSize(tx.getLong("size"));
        transactionInBlockDto.setWeight(tx.getString("weight"));
        JSONArray vouts = tx.getJSONArray("vout");
        List<TxDetailTxInfo> txDetailTxInfos=new ArrayList<>();
        for (int i=0;i<vouts.size();i++){
            TxDetailTxInfo txDetailTxInfo = importVoutDetail(vouts.getJSONObject(i));
            txDetailTxInfos.add(txDetailTxInfo);
        }

        JSONArray vins = tx.getJSONArray("vin");
        for (int i = 0; i < vins.size(); i++) {
            TxDetailTxInfo txDetailTxInfo = importVinDetail(vins.getJSONObject(i),bitcoinClient);
            txDetailTxInfos.add(txDetailTxInfo);
        }

        transactionInBlockDto.setTxDetailTxInfo(txDetailTxInfos);
        transactionInBlockDto.setInput(inputTotal);
        transactionInBlockDto.setOutput(outputTotal);
        return transactionInBlockDto;
    }

    public  TxDetailTxInfo importVoutDetail(JSONObject vout){
        TxDetailTxInfo txDetailTxInfo = new TxDetailTxInfo();
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null && !addresses.isEmpty()){
            txDetailTxInfo.setAddress(addresses.getString(0));
        }
        txDetailTxInfo.setAmount(vout.getDouble("value"));
        txDetailTxInfo.setType((byte) TransactionEnums.Receive.ordinal());
        outputTotal+=vout.getDouble("value");
        return txDetailTxInfo;
    }

    public  TxDetailTxInfo importVinDetail(JSONObject vin, BitcoinClient bitcoinClient) throws Throwable {
        String txid = vin.getString("txid");
        Integer vout=vin.getInteger("vout");
        TxDetailTxInfo txDetailTxInfo = new TxDetailTxInfo();
        if (txid==null){
            return txDetailTxInfo;
        }
        JSONObject rawTransaxtion = bitcoinClient.getRawTransaxtion(txid);
        JSONArray vouts = rawTransaxtion.getJSONArray("vout");

        JSONObject jsonObject = vouts.getJSONObject(vout);
        txDetailTxInfo.setType((byte) TransactionEnums.Send.ordinal());
        Double amount = jsonObject.getDouble("value");
        txDetailTxInfo.setAmount(amount);
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null && !addresses.isEmpty()){
            txDetailTxInfo.setAddress(addresses.getString(0));
        }
        inputTotal+=amount;

        return txDetailTxInfo;
    }


}
