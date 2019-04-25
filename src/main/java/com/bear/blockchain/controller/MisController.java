package com.bear.blockchain.controller;

import com.bear.blockchain.api.BitApi;
import com.bear.blockchain.api.BitcoinClient;
import com.bear.blockchain.service.BlockService;
import com.bear.blockchain.service.MiscService;
import com.bear.blockchain.dto.OutputStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mis")
@EnableAutoConfiguration
@CrossOrigin
public class MisController {

    @Autowired
    private MiscService miscService;

    @Autowired
    private BitApi bitApi;

    @Autowired
    private BlockService blockService;

    @Autowired
    private BitcoinClient bitcoinClient;

    @GetMapping("/serach")
    public Object search(
            @RequestParam String keyword
    ){
        return null;
    }

    @PostMapping("/OutputFromHeight")
    public void OutputFromHeight(

            //是否清空
            @RequestParam Boolean isClean
    ){

    }

    @GetMapping("/OutputFromHash")
    public void OutputFromHash(
            @RequestParam(required = false,defaultValue ="" ) String blockhash,

            @RequestParam(required = false,defaultValue = "false") Boolean isClean
    ) throws Throwable {

        //查询数据库
        Integer height=blockService.getMaxHeight();
        //判断 是否从新开始同步
        if (height==null){
            //blockhash = bitcoinClient.getStartblockhash();
            miscService.OutputFromHash(blockhash,isClean);
        }else {
            blockhash = bitcoinClient.getBlockHashByHeight(height+1);
            miscService.OutputFromHash(blockhash,isClean);
        }

        //todo io流 save height



    }

    @GetMapping("/getOutputStatus")
    public OutputStatusDto getOutputStatus(
    ){
        return null;
    }

    public void logHeight(){

    }

}
