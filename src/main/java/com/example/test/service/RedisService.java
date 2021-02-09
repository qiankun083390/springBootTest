package com.example.test.service;

public interface RedisService {

    String sayHello(String s);

    void setValue(String key,Object value,long time);

    String getValue(String key);
}

