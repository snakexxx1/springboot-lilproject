package com.snake.springbootlilproject.service;

import com.snake.springbootlilproject.pojo.Emp;
import com.snake.springbootlilproject.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /*
    * 分页查询
    * */
    PageBean page(Integer page, Integer pageSize,String name, Short gender,
                  LocalDate begin, LocalDate end);
    /*
    * 批量删除
    * */
    void delete(List<Integer> ids);

    /*
     *
     * 新增员工
     * */
    void save(Emp emp);


    /*
     * 根据ID查询
     * */
    Emp getById(Integer id);
    /*
     * 修改员工
     * */
    void update(Emp emp);
    /*
     * 基础登录
     * */
    Emp login(Emp emp);
}
