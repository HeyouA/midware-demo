package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class User{

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Date updateTime;

    private Date createTime;

}