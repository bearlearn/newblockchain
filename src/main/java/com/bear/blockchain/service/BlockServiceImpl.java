package com.bear.blockchain.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bear.blockchain.api.BitcoinClient;
import com.bear.blockchain.dao.BlockMapper;
import com.bear.blockchain.dto.BlockDetailDto;
import com.bear.blockchain.dto.BlockListDto;
import com.bear.blockchain.dto.TransactionInBlockDto;
import com.bear.blockchain.dto.TxDetailTxInfo;
import com.bear.blockchain.enums.TransactionEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinClient bitcoinClient;

    @Override
    public Integer getMaxHeight() {
        return blockMapper.getMaxHeight();
    }

    @Override
    public List<BlockListDto> getRecentBlocks(Integer blockchainId) {


        return blockMapper.getRecentBlocks(blockchainId);
    }

    @Override
    public BlockDetailDto getBlockDetailDto(JSONObject block) throws Throwable {
        BlockDetailDto blockDetailDto = new BlockDetailDto();
        blockDetailDto.setBlockhash(block.getString("hash"));
        blockDetailDto.setHeight(block.getInteger("height"));
        blockDetailDto.setMerkleRoot(block.getString("merkleroot"));
        blockDetailDto.setDifficulty(block.getDouble("difficulty"));
        blockDetailDto.setNextBlockhash(block.getString("nextblockhash"));
        blockDetailDto.setPrevBlockhash(block.getString("previousblockhash"));
        blockDetailDto.setSizeOnDisk(block.getLong("size"));
        JSONArray txs = block.getJSONArray("tx");
        List<TransactionInBlockDto> transactionInBlockDtos=new ArrayList<>();
        for (int i = 0; i < txs.size(); i++) {
            TransactionInBlockDto  transactionInBlockDto= importTx(txs.getJSONObject(i),new Date(block.getLong("time")*1000));
            transactionInBlockDtos.add(transactionInBlockDto);
        }
        blockDetailDto.setTransactions(transactionInBlockDtos);
        return blockDetailDto;
    }

    public TransactionInBlockDto importTx(JSONObject tx, Date time) throws Throwable {
        TransactionInBlockDto transactionInBlockDto = new TransactionInBlockDto();
        String txid = tx.getString("txid");
        transactionInBlockDto.setTxid(txid);
        transactionInBlockDto.setTxhash(tx.getString("hash"));
        transactionInBlockDto.setTime(time);
        transactionInBlockDto.setSize(tx.getLong("size"));


        JSONArray vouts = tx.getJSONArray("vout");
        List<TxDetailTxInfo> txDetailTxInfos=new ArrayList<>();
        for (int i=0;i<vouts.size();i++){
            TxDetailTxInfo txDetailTxInfo = importVoutDetail(vouts.getJSONObject(i));
            txDetailTxInfos.add(txDetailTxInfo);
        }

        JSONArray vins = tx.getJSONArray("vin");
        for (int i = 1; i < vins.size(); i++) {
            TxDetailTxInfo txDetailTxInfo = importVinDetail(vins.getJSONObject(i));
            txDetailTxInfos.add(txDetailTxInfo);
        }

        transactionInBlockDto.setTxDetailTxInfo(txDetailTxInfos);

        return transactionInBlockDto;

    }

    public TxDetailTxInfo importVoutDetail(JSONObject vout){
        TxDetailTxInfo txDetailTxInfo = new TxDetailTxInfo();
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null && !addresses.isEmpty()){
            txDetailTxInfo.setAddress(addresses.getString(0));
        }
        txDetailTxInfo.setAmount(vout.getDouble("value"));
        txDetailTxInfo.setType((byte) TransactionEnums.Receive.ordinal());
        return txDetailTxInfo;
    }

    public TxDetailTxInfo importVinDetail(JSONObject vin) throws Throwable {
        String txid = vin.getString("txid");
        Integer vout=vin.getInteger("vout");

        JSONObject rawTransaxtion = bitcoinClient.getRawTransaxtion(txid);
        JSONArray vouts = rawTransaxtion.getJSONArray("vout");
        JSONObject jsonObject = vouts.getJSONObject(vout);

        TxDetailTxInfo txDetailTxInfo = new TxDetailTxInfo();
        txDetailTxInfo.setType((byte) TransactionEnums.Send.ordinal());
        Double amount = jsonObject.getDouble("value");
        txDetailTxInfo.setAmount(amount);
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null && !addresses.isEmpty()){
            txDetailTxInfo.setAddress(addresses.getString(0));
        }

        return txDetailTxInfo;

    }

}
