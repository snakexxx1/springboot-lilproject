package com.snake.springbootlilproject.controller;

import com.snake.springbootlilproject.pojo.Emp;
import com.snake.springbootlilproject.pojo.Result;
import com.snake.springbootlilproject.service.EmpService;
import com.snake.springbootlilproject.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    /*
    * 基础登录
    * */
    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp){
        log.info("员工登录：{}",emp);
        Emp e = empService.login(emp);
        //登录成功生成令牌并下发
        if(e!=null) {
            Map<String,Object> claims =new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);//jwt包含当前登录员工信息
            return Result.success(jwt);
        }
        //登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
