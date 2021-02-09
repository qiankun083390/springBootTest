package com.example.test.bean;

import lombok.Data;

import java.io.Serializable;
/**
 * @ClassName UserRedisModel
 * @Desc 封装用户信息（Redis用）
 * @Date
 * @Version 1.0
 */
@Data
public class UserRedisModel implements Serializable {

    private static final long serialVersionUID = 6659067190960087996L;

    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
}
