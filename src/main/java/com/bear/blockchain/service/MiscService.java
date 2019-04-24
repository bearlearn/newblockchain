package com.bear.blockchain.service;


public interface MiscService {


   void OutputFromHeight(Integer blockHeight, Boolean isClean);


   void OutputFromHash(String blockhash, Boolean isClean);


}
