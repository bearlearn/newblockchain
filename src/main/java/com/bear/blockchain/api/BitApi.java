package com.bear.blockchain.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:18332" ,name = "bitcoin")

public interface BitApi {

    /**
     * 返回区块链信息
     * @return
     */
    @GetMapping("/rest/chaininfo.json")
    JSONObject getChainInfo();

    /**
     * 根据hash获取交易信息
     * @param txhash
     * @return
     */
    @GetMapping("/rest/tx/{txhash}.json")
    JSONObject getTransaction(@PathVariable("txhash") String txhash);

    /**
     * 返回 块的信息
     * @param blockHash
     * @return
     */
    @GetMapping("/rest/block/{blockhash}.json")
    JSONObject getBlock(@PathVariable("blockhash") String blockHash);

    /**
     *  通过blockhash 返回信息
     * @param blockhash
     * @return
     */
    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getNoTxBlock(@PathVariable("blockhash") String blockhash);

    /**
     * 返回这个 block头
     * @param count
     * @param blockhash
     * @return
     */
    @GetMapping("/rest/headers/{count}/{blockhash}.json")

    JSONArray getBlockHeadersArray(@PathVariable("count") Integer count, @PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempool();


    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();

    @GetMapping("/rest/tx/txhash.json")
    JSONObject gettransation(@PathVariable("txhash") String txhash);



}
