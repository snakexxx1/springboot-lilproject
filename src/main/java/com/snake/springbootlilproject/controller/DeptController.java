package com.snake.springbootlilproject.controller;

import com.snake.springbootlilproject.anno.Log;
import com.snake.springbootlilproject.pojo.Dept;
import com.snake.springbootlilproject.pojo.Result;
import com.snake.springbootlilproject.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    /*定义日志记录对象 添加lomback中的@slf4j 注解就行
      private static Logger log = LoggerFactory.getLogger(DeptController.class);*/
    @Autowired
    private DeptService deptService;  //依赖注入

    /*
    *
    * 查询部门数据
    * */
    //@RequestMapping("/depts") //接口文档已经说明
    @GetMapping //指定请求方式为GET（简单便捷）
    //定义方法，查询部门信息,返回值是统一响应结果
    public Result list(){
        log.info("查询全部部门数据");

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return  Result.success(deptList);
    }


    /*
    *
    * 删除部门数据
    * */
    @Log
    @DeleteMapping("/{id}")   //@PathVariable用来获取路径参数绑定id
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }


    /*
    *
    * 新增部门数据
    * */
    @Log
      @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("根据名字添加部门：{}",dept);
        //调用service添加部门
        deptService.add(dept);
        return  Result.success();
    }

    /*
    * 根据ID查询
    * */
    @GetMapping("/{id}")
    public Result GETById(@PathVariable Integer id){
        log.info("根据ID查询部门：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*
    *修改部门
    **/
    @PutMapping
    @Log
    public Result Update(@RequestBody Dept dept){
        log.info("根据名字修改部门：{}",dept);
        deptService.update(dept);
        return  Result.success();
    }
}
