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

import java.time.LocalDate;
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
    private MisController miscController;

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
        // miscController.OutputFromHash(tempBlockhash,false);



        //最新的hash值
        String bestblockhash = bitcoinClient.getbestblockhash();
        //创建list容器
        List<BlockListDto> blockListDtos=new LinkedList<>();
        String tempBlockhash=bestblockhash;

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
     * 页面块信息列表,分页
     * @param isPage
     * @return
     */
    @GetMapping("getBlockView")
    public List<BlockViewDto> getBlockView(
            @RequestParam(required = false,defaultValue = "") String isPage){
        LocalDate now = LocalDate.now();
        String date = now.toString();
        String[] split = date.split("-");
        int day=0;
        if (isPage.equals("pre")){
            int i = Integer.parseInt(split[2]);
            day= i - 1;
        }else if (isPage.equals("next")){
            int i = Integer.parseInt(split[2]);
            day= i + 1;
        }else {
            day=Integer.parseInt(split[2]);
        }
        LocalDate nowDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), day);
        System.out.println(nowDate.toString());
        List<BlockViewDto> viewDTOS = blockMapper.viewMore(nowDate.toString());

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
