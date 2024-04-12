package com.snake.springbootlilproject.controller;

import com.snake.springbootlilproject.anno.Log;
import com.snake.springbootlilproject.pojo.Emp;
import com.snake.springbootlilproject.pojo.PageBean;
import com.snake.springbootlilproject.pojo.Result;
import com.snake.springbootlilproject.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*
* 员工管理
* */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;


    /*
    * 分页查询
    * */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
       log.info("分页查询,参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
       //调用service
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);//响应结果
    }


    /*
    *
    * 删除员工
    * */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操,ids:{}",ids);
            empService.delete(ids);
            return Result.success();
    }

    /*
    *
    * 新增员工
    * */
    @PostMapping
    @Log
    public Result save(@RequestBody Emp emp){
       log.info("新增员工，emp：{}",emp);
       empService.save(emp);
       return Result.success();
    }

    /*
    * 根据ID查询
    * */
    @GetMapping("/{id}")
   public  Result getById(@PathVariable Integer id){
       log.info("根据id查询员工信息，id：{}",id);
       Emp emp = empService.getById(id);
        return Result.success(emp);
   }

   /*
   * 修改员工
   * */
    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息，{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
