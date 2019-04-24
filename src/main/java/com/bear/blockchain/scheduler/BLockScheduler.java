package com.bear.blockchain.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class BLockScheduler {

    private Logger logger= LoggerFactory.getLogger(BLockScheduler.class);

    @Scheduled(fixedRate = 60*10*1000)

    public void outPutBlockTransaction(){
        logger.info("start output block transaction");
    }
}
