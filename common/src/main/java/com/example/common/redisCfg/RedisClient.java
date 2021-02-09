package com.example.common.redisCfg;

import com.example.common.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisClient
 * @Desc Redis 工具类
 * @Date
 * @Version 1.0
 */
@Component
public class RedisClient {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置指定 key 的值
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time == Constants.CACHE_EXP_FOREVER) {
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取指定 key 的值
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        if(redisTemplate.hasKey(key)){
            return  (T)redisTemplate.opsForValue().get(key);
        }else{
            return null;
        }
    }

    /*
     * @ClassName RedisClient
     * @Desc  删除缓存，并返回是否删除成功
     * @Date
     * @Version 1.0
     */
    public boolean delete(String key){
        redisTemplate.delete(key);
        // 如果还存在这个 key 就证明删除失败
        if(redisTemplate.hasKey(key)){
            return false;
            // 不存在就证明删除成功
        }else{
            return true;
        }
    }

    /*
     * @ClassName RedisClient
     * @Desc 获取失效时间（-2：失效 / -1：没有时间限制）
     * @Date
     * @Version 1.0
     */
    public long getExpire(String key){
        // 判断是否存在
        if(redisTemplate.hasKey(key)){
            return redisTemplate.getExpire(key);
        }else{
            return Long.parseLong(-2+"");
        }
    }
}