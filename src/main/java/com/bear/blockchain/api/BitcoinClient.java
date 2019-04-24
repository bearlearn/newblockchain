package com.bear.blockchain.api;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class BitcoinClient {

    private JsonRpcHttpClient jsonRpcHttpClient;

    //构造器初始化 jsonRpcHttpClient
    public BitcoinClient() throws MalformedURLException {
        //创建map容器
        Map<String,String> headers=new HashMap<>();
        //author
        String author = String.format("%s:%s", "bear", "123456");
        //进行base64编码
        String authorBase = Base64.getEncoder().encodeToString(author.getBytes());

        String authorResult = String.format("Basic %s", authorBase);

        headers.put("Authorization",authorResult);

        jsonRpcHttpClient = new JsonRpcHttpClient(new URL("http://localhost:18332"), headers);

    }
    //
    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String blockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return blockhash;
    }

}
