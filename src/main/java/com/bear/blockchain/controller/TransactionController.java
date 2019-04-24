package com.bear.blockchain.controller;

import com.bear.blockchain.dto.TransactionInfoDto;
import com.bear.blockchain.dto.TransationListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping("getRecentTransactionsByID")
    public List<TransationListDto> getRecentTransactionsByID(
            @RequestParam Integer blockchainId
            ){
        return null;
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

    @RequestMapping("getTransactionInfoByTxhash")
    public TransactionInfoDto getTransactionInfoByTxhash(
            @RequestParam String txHash
    ){
        return null;
    }


}
