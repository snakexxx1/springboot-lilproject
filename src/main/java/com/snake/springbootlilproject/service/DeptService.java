package com.snake.springbootlilproject.service;

import com.snake.springbootlilproject.pojo.Dept;

import java.util.List;

public interface DeptService {



    //查询全部部门数据
    List<Dept> list();
    //删除部门
    void delete(Integer id);
     //新增部门
    void add(Dept dept);
     //根据ID查询部门
    Dept getById(Integer id);
    //修改部门
    void update(Dept dept);
}
