package com.jc.dubbo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jincheng.zhang
 */
@Data
public class User implements Serializable {
    private String name;
    private String desc;
}
