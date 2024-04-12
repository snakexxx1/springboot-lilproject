package com.snake.springbootlilproject.service.impl;

import com.snake.springbootlilproject.mapper.DeptMapper;
import com.snake.springbootlilproject.mapper.EmpMapper;
import com.snake.springbootlilproject.pojo.Dept;
import com.snake.springbootlilproject.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
    @Transactional(rollbackFor = Exception.class)//spring事务管理//指定所有异常都会回滚
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);//根据ID删除部门数据
        empMapper.deleteByDeptId(id);//根据部门ID来删除该部门下员工
    }

  //  @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


}
