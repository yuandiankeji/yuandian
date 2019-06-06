package com.yuandian.service.impl;

import com.yuandian.entity.LoginPO;
import com.yuandian.entity.LoginPOExample;
import com.yuandian.mapper.LoginPOMapper;
import com.yuandian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: luyufeng
 * @Date: 2019/4/6
 */

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private LoginPOMapper loginPOMapper;

    @Override
    public LoginPO insert(LoginPO loginPO) {
        loginPOMapper.insert(loginPO);
        return loginPO;
    }

    @Override
    public LoginPO selectByUid(long uid) {
        return loginPOMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void update(LoginPO loginPO) {
        loginPOMapper.updateByPrimaryKeySelective(loginPO);
    }

    @Override
    public LoginPO selectByPhone(String phone) {
        LoginPOExample example = new LoginPOExample();
        LoginPOExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<LoginPO> list = loginPOMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return null;
        }
        return loginPOMapper.selectByExample(example).get(0);
    }


}
