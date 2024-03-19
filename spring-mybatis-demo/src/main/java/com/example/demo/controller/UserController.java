package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserController userController;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/selectUserById")
    public User selectUserById() {
        String tmp = "{\"id\":1}";
        JSONObject jsonObject = JSONObject.parseObject(tmp);
        return userMapper.selectUserById(jsonObject);
    }

}