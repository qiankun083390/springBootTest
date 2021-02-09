package com.example.test.serviceImpl;

import com.example.common.redisCfg.RedisClient;
import com.example.test.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisClient redisClient;

    @Override
    public String sayHello(String s) {
        System.out.println("qk say:" + s);
        return s;
    }

    /**
     * 设置key，value存入redis
     * @param key
     * @param value
     * @param time
     */
    @Override
    public void setValue(String key, Object value, long time) {
        redisClient.set(key, value, time);
    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    @Override
    public String getValue(String key) {
        return redisClient.get(key) == null ? "redis key time out" : redisClient.get(key);
    }
}