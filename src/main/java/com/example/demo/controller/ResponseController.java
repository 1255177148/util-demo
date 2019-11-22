package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.RemoteException;
import com.example.demo.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Author hezhan
 * @Date 2019/11/22 11:05
 */
@RestController
@RequestMapping("/api/v1")
public class ResponseController {

    @Autowired
    ResponseService responseService;

    @GetMapping("/testHeader")
    public User getMessage(@RequestHeader("name") String name){
        try {
            name = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RemoteException("URLDecoder decode header参数失败");
        }
        return responseService.getResponse(name);
    }
}
