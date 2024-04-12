package com.snake.springbootlilproject.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.springbootlilproject.mapper.EmpMapper;
import com.snake.springbootlilproject.pojo.Emp;
import com.snake.springbootlilproject.pojo.PageBean;
import com.snake.springbootlilproject.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //@Override
   /* public PageBean page(Integer page, Integer pageSize) {
          //1.获取总记录数
        Long count = empMapper.count();
        //2.获取分页查询结果
        Integer start =(page - 1)*pageSize;
        List<Emp> emplist = empMapper.page(start, pageSize);
        //3.封装pagebean对象
        PageBean pageBean = new PageBean(count,emplist);
        return pageBean;
    }*/
    /*
    * 根据条件分页查询
    * */
    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询参数
        List<Emp> emplist = empMapper.list(name, gender, begin, end);
        Page<Emp> p =(Page<Emp>) emplist;//强转为page类型

        //3.封装pagebean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
    /*
    * 批量删除
    * */
    @Override
    public void delete(List<Integer> ids) {
       empMapper.delete(ids);
    }

    /*
     *
     * 新增员工
     * */
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }



    /*
     * 根据ID查询
     * */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }


    /*
     * 修改员工
     * */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }


    /*
     * 基础登录
     * */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
