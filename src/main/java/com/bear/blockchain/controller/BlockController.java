package com.bear.blockchain.controller;

import com.bear.blockchain.dto.BlockDetailDto;
import com.bear.blockchain.dto.BlockListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/block")
public class BlockController {

    /**
     * 根据ID查询块
     * @param blockchainId
     * @return
     */
    @GetMapping("/getRecentBlocksById")
    public List<BlockListDto> getRecentBlocks(
            @RequestParam Integer blockchainId

    ){
        return null;
    }

    /**
     * 根据币种和类型查询块
     * @param name
     * @param type
     * @return
     */
    @GetMapping("/getRecentBlocksByNameAndType")
    public List<BlockListDto> getRecentBlocks(
            @RequestParam String name,
            @RequestParam String type

    ){
        return null;
    }

    /**
     * 根据hash查询块
     * @param blockHash
     * @return
     */
    @GetMapping("getBlockDetailByHash")
    public BlockDetailDto getBlockDetailByHash(
            @RequestParam String blockHash

    ){
        return null;
    }

    /**
     * 根据块高度查询块
     * @param blockHeight
     * @return
     */
    @RequestMapping("getBlockDetailByHeight")
    public BlockDetailDto getBlockDetailByHeight(
            @RequestParam String blockHeight

    ){
        return null;
    }


}
