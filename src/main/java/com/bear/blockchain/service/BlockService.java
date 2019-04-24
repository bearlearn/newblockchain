package com.bear.blockchain.service;

import com.alibaba.fastjson.JSONObject;
import com.bear.blockchain.dto.BlockDetailDto;
import com.bear.blockchain.dto.BlockListDto;

import java.util.List;

public interface BlockService {


    Integer getMaxHeight();


    List<BlockListDto> getRecentBlocks(Integer blockchainId);


    BlockDetailDto getBlockDetailDto(JSONObject block) throws Throwable;

}
