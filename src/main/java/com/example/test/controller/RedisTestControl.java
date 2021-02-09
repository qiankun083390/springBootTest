package com.example.test.controller;

import com.example.common.constants.Constants;
import com.example.test.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RedisTestControl")
public class RedisTestControl {

    @Autowired
    private RedisService redisService;

    @GetMapping("/hello")
    public void sayHello(){
        String str = redisService.sayHello("Not hello, just hi. ");
        System.out.println("consumer get str = " + str + " from provide");
    }

    @GetMapping("/setValue")
    public String setValue(String key, String value){

        //设置key有效时间为10s，便于测试
        redisService.setValue(key,value, Constants.CACHE_EXP_FOREVER);
        return "key: " + key +" value: " + value;
    }

    @GetMapping("/getValue")
    public String getValue(String key){
        System.out.println(redisService.getValue(key));
        return redisService.getValue(key);
    }


}