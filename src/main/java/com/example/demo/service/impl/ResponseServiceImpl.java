package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.ResponseService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author hezhan
 * @Date 2019/11/22 11:09
 */
@Service
public class ResponseServiceImpl implements ResponseService {

    @Override
    public User getResponse(String name) {
        User user = new User();
        user.setName(name);
        user.setDate(new Date());
        user.setAge(3);
        return user;
    }
}
