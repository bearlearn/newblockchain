package com.bear.blockchain.controller;

import com.alibaba.fastjson.JSONObject;
import com.bear.blockchain.api.BitApi;
import com.bear.blockchain.api.BitcoinClient;
import com.bear.blockchain.dao.BlockMapper;
import com.bear.blockchain.dto.BlockDetailDto;
import com.bear.blockchain.dto.BlockListDto;
import com.bear.blockchain.dto.BlockViewDto;
import com.bear.blockchain.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/block")
@EnableAutoConfiguration
@CrossOrigin
public class BlockController {



    @Autowired
    private BitApi bitApi;

    @Autowired
    private BitcoinClient bitcoinClient;

    @Autowired
    private BlockService blockService;

    @Autowired
    private MisController misController;

    @Autowired
    private BlockMapper blockMapper;


    /**
     * 根据ID查询块
     * @param blockchainId
     * @return
     */
    @GetMapping("/getRecentBlocksById")
    public List<BlockListDto> getRecentBlocks(
            @RequestParam Integer blockchainId

    ) throws Throwable {


        //List<BlockListDto> blockListDtos=blockService.getRecentBlocks(blockchainId);




        //最新的hash值
        String bestblockhash = bitcoinClient.getbestblockhash();
        //创建list容器
        List<BlockListDto> blockListDtos=new LinkedList<>();
        String tempBlockhash=bestblockhash;
         misController.OutputFromHash(tempBlockhash,false);
        for (int i=0;i<5;i++){

            JSONObject block = bitApi.getNoTxBlock(tempBlockhash);

            BlockListDto blockListDTO = new BlockListDto();

            blockListDTO.setHeight(block.getInteger("height"));

            Long time = block.getLong("time");

            blockListDTO.setTime(time * 1000);

            blockListDTO.setTxSize(block.getJSONArray("tx").size());

            blockListDTO.setSizeOnDisk(block.getLong("size"));

            blockListDtos.add(blockListDTO);

            tempBlockhash = block.getString("previousblockhash");
        }

        return blockListDtos;

    }

    /**
     * 页面块信息列表,并且判断是上一页还是下一页
     * 并且根据前端选择的时间去同步数据
     * @param isPage
     * @return
     */
    @GetMapping("getBlockView")
    public List<BlockViewDto> getBlockView(
            @RequestParam(required = false,defaultValue = "") String isPage,
            @RequestParam(required = false,defaultValue = "") Long now){
        String newTime=null;
        if (now.equals("")){
            newTime=LocalDate.now().toString();
        }else {
            Date date = new Date(now);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            newTime = simpleDateFormat.format(date);
        }

        List<BlockViewDto> viewDTOS = blockMapper.viewMore(newTime);

        return viewDTOS;
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
