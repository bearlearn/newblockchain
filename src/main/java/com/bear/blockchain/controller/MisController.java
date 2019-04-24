package com.bear.blockchain.controller;

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

    @GetMapping("/serach")
    public Object search(
            @RequestParam String keyword

    ){
        return null;
    }

    @PostMapping("/OutputFromHeight")
    public void OutputFromHeight(
            @RequestParam Integer blockHeight,

            @RequestParam Boolean isClean
    ){

    }

    @PostMapping("/OutputFromHash")
    public void OutputFromHash(
            @RequestParam String blockHash,

            @RequestParam Boolean isClean
    ){
        miscService.OutputFromHash(blockHash,isClean);
    }

    /**
     *
     * @return
     */
    @GetMapping("/getOutputStatus")
    public OutputStatusDto getOutputStatus(
    ){
        return null;
    }

}
