package com.example.test;

import com.example.common.constants.Constants;
import com.example.test.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisService redisService;

    @Before
    public void setup(){
        redisService.setValue("mqx","heihei", Constants.CACHE_EXP_FOREVER);    }

    @Test
    public void get(){
        logger.info("=================user==================name:{}",  redisService.getValue("mqx"));

    }

}
