package com.bear.blockchain.dao;

import com.bear.blockchain.dto.BlockListDto;
import com.bear.blockchain.dto.BlockViewDto;
import com.bear.blockchain.po.Block;
import org.apache.ibatis.annotations.Param;

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

   List<BlockListDto> getRecentBlocks(@Param("blockchainId")Integer blockchainId);

    List<BlockViewDto> viewMore(@Param("nowDate")String nowDate);
}