package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

    @Autowired
    private TestServiceImpl testService;

	@Test
	void selectById() {
		userMapper.selectById(1);
		userMapper.selectById(2);
	}

	@Test
	void selectUserById() {
		String tmp = "{\"id\":1}";
		JSONObject jsonObject = JSONObject.parseObject(tmp);
		userMapper.selectUserById(jsonObject);
	}

}
