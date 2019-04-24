package com.bear.blockchain.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BitcoinClient {

    @Autowired
    private JsonRpcHttpClient jsonRpcHttpClient;

    /**
     * 获取块hash根据块高度
     * @param blockHeight
     * @return
     * @throws Throwable
     */
    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String blockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return blockhash;
    }

    /**
     * 钱包余额
     * @param address
     * @return
     * @throws Throwable
     */
    public JSONObject getBalance(String address) throws Throwable {
        JSONArray balances = jsonRpcHttpClient.invoke("listunspent", new Object[]{6, 9999999, new String[]{address}}, JSONArray.class);
        int size = balances.size();
        System.out.println(size);
        Double amounts=0.0;
        for (int i=0;i<size;i++){
            Double amount = balances.getJSONObject(i).getDouble("amount");
            amounts+=amount;
        }
        JSONObject jsonObject = balances.getJSONObject(0);
        jsonObject.put("amount",amounts);
        return jsonObject;
    }

    /**
     * 获取最近快的hash地址
     * @return
     * @throws Throwable
     */
    public String  getbestblockhash() throws Throwable {
        String getbestblockhash = jsonRpcHttpClient.invoke("getbestblockhash", new String[]{}, String.class);
        return getbestblockhash;
    }

    /**
     * 获取初始区块hash地址
     * @param height
     * @return
     * @throws Throwable
     */
    public String getStartblockhash(Integer height) throws Throwable {
        String getblockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{height}, String.class);
        return getblockhash;
    }



    /**
     *  //通过txid 获取原始交易信息
     * @param txid
     * @return
     * @throws Throwable
     */
    public JSONObject getRawTransaxtion(String txid) throws Throwable {
        JSONObject rawTransaction = jsonRpcHttpClient.invoke("getrawtransaction", new Object[]{txid, true}, JSONObject.class);
        return rawTransaction;
    }

}
