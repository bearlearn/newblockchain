package com.bear.blockchain.dao;

import com.bear.blockchain.dto.BlockListDto;
import com.bear.blockchain.po.Block;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockhash);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(String blockhash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    void cleanTable();

    Integer getMaxHeight();

    List<BlockListDto> getRecentBlocks(Integer blockchainId);
}