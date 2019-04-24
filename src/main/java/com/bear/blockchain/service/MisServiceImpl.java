package com.bear.blockchain.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bear.blockchain.api.BitApi;
import com.bear.blockchain.api.BitcoinClient;
import com.bear.blockchain.dao.BlockMapper;
import com.bear.blockchain.dao.TransactionDetailMapper;
import com.bear.blockchain.dao.TransactionMapper;
import com.bear.blockchain.enums.TransactionEnums;
import com.bear.blockchain.po.Block;
import com.bear.blockchain.po.Transaction;
import com.bear.blockchain.po.TransactionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class MisServiceImpl implements MiscService {


    @Autowired
    private BitApi bitApi;

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionDetailMapper transactionDetailMapper;

    @Autowired
    private BitcoinClient bitcoinClient;
    @Async
    @Override
    public void OutputFromHeight(Integer blockHeight, Boolean isClean) {

    }

    @Async
    @Override
    public void OutputFromHash(String blockhash,
                               Boolean isClean) {
        if (isClean){
            blockMapper.cleanTable();
        }
        do {
            //同步块信息
            JSONObject block = bitApi.getBlock(blockhash);

            try {
                //递归
                saveBlock(block);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            String hash = block.getString("nextblockhash");
            blockhash= hash;
        }while (blockhash!=null && !blockhash.isEmpty());
    }


    public void saveBlock(JSONObject jsonObject) throws Throwable {
        Block block = new Block();
        block.setBlockhash(jsonObject.getString("hash"));
        block.setBlockchainId(2);
        block.setHeight(jsonObject.getInteger("height"));
        Long time = jsonObject.getLong("time");
        Date date = new Date(time * 1000);
        block.setTime(date);
        JSONArray txs = jsonObject.getJSONArray("tx");
        for (int i = 0; i < txs.size(); i++) {
            importTx(txs.getJSONObject(i),jsonObject.getString("hash"),date);
        }
        block.setTxSize(txs.size());
        block.setSizeOnDisk(jsonObject.getLong("size"));
        block.setDifficulty(jsonObject.getDouble("difficulty"));
        block.setPrevBlockhash(jsonObject.getString("previousblockhash"));
        block.setNextBlockhash(jsonObject.getString("nextblockhash"));
        block.setMerkleRoot(jsonObject.getString("merkleroot"));

        blockMapper.insert(block);
    }


    public void importTx(JSONObject tx,String blockhash,Date time) throws Throwable {
        Transaction transaction = new Transaction();
        String txid = tx.getString("txid");
        transaction.setTxid(txid);
        transaction.setWeight(tx.getInteger("weight"));
        transaction.setTxhash(tx.getString("hash"));
        transaction.setBlockhash(blockhash);
        transaction.setSize(tx.getLong("size"));
        transaction.setTime(time);

        JSONArray vouts = tx.getJSONArray("vout");
        for (int i=0;i<vouts.size();i++){
            importVoutDetail(vouts.getJSONObject(i), txid);
        }

        JSONArray vins = tx.getJSONArray("vin");
        for (int i = 1; i < vins.size(); i++) {
            importVinDetail(vins.getJSONObject(i),txid);
        }

        transactionMapper.insert(transaction);

    }

    public void importVoutDetail(JSONObject vout,String txid){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTxHash(txid);
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null && !addresses.isEmpty()){
            transactionDetail.setAddress(addresses.getString(0));
        }
        transactionDetail.setAmount(vout.getDouble("value"));
        transactionDetail.setType((byte) TransactionEnums.Receive.ordinal());

        transactionDetailMapper.insert(transactionDetail);
    }

    public void importVinDetail(JSONObject vin,String txidOrigin) throws Throwable {
        String txid = vin.getString("txid");
        Integer vout=vin.getInteger("vout");

        JSONObject rawTransaxtion = bitcoinClient.getRawTransaxtion(txid);
        JSONArray vouts = rawTransaxtion.getJSONArray("vout");
        JSONObject jsonObject = vouts.getJSONObject(vout);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTxHash(txidOrigin);
        transactionDetail.setType((byte) TransactionEnums.Send.ordinal());
        Double amount = jsonObject.getDouble("value");
        transactionDetail.setAmount(amount);
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null && !addresses.isEmpty()){
            transactionDetail.setAddress(addresses.getString(0));
        }

        transactionDetailMapper.insert(transactionDetail);
    }
}
