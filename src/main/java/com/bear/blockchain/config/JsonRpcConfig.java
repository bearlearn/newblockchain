package com.bear.blockchain.config;


import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始化jsonRpc
 */
@Component
@PropertySource("classpath:application.properties")
public class JsonRpcConfig {

    @Value("${jsonRpc.account}")
    private String  account;

    @Value("${jsonRpc.password}")
    private String password;

    @Value("${jsonRpc.url}")
    private String url;

    @Bean
    private JsonRpcHttpClient JsonRpcClient() throws MalformedURLException {

        //创建map容器
        Map<String,String> headers=new HashMap<>();
        //author
        String author = String.format("%s:%s", account, password);
        //进行base64编码
        String authorBase = Base64.getEncoder().encodeToString(author.getBytes());
        String authorResult = String.format("Basic %s", authorBase);
        headers.put("Authorization",authorResult);
        return new JsonRpcHttpClient(new URL(url), headers);

    }


}
