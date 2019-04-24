package com.bear.blockchain.controller;

import com.bear.blockchain.dto.AddresDto;
import com.bear.blockchain.dto.TransactionInBlockDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/address")
public class AddresController {
    /**
     *
     * @param address
     * @return
     */
    @GetMapping("getAddressInfo")
    public AddresDto getAddressInfo(
            @RequestParam String address
    ){
        return null;
    }

    /**
     * 根据
     * @param address
     * @param pageNum
     * @return
     */
    @GetMapping("getAddressTransactions")
    public List<TransactionInBlockDto> getAddressTransactions(
            @RequestParam String address,
            @RequestParam(defaultValue = "1",required = false) Integer pageNum


    ){

        return null;
    }
}
