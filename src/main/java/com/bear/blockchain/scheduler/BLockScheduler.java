package com.bear.blockchain.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BLockScheduler {

    private Logger logger= LoggerFactory.getLogger(BLockScheduler.class);

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Scheduled(fixedRate = 60*10*1000)
    public void outPutBlockTransaction(){

        logger.info("start output block transaction");

        simpMessageSendingOperations.convertAndSend("/bitcoin/block","/block/getRecentBlocks");

    }
}
