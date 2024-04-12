package com.snake.springbootlilproject.pojo;

import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* 分页查询结果封装类
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private long total;//总记录数
    private List rows;//数据列表
    //切记与接口文档要保持一致
}
