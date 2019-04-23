package com.yuandian.core.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @author: luyufeng
 * @Date: 2019/4/5
 */

@Data
public class UserPO {
    private Long uid;
    private String account;
    private String phoneNum;
    private String mail;
    private Integer sex;
    private String passWord;
    private String nickName;
    private String signature;
    private String qq;
    private String wechat;
    private String alipay;
    private String ip;
    private Date registration;
    private String hAddress;

}
