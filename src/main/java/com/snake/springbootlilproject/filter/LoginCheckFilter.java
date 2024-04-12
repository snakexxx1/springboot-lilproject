package com.snake.springbootlilproject.filter;

import com.alibaba.fastjson.JSONObject;
import com.snake.springbootlilproject.pojo.Result;
import com.snake.springbootlilproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest )request;
        HttpServletResponse resq =(HttpServletResponse)response;
        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}",url);
        //2.判断请求中url是否包含login，包含就放行，说明是登录请求
        if(url.contains("login")){
            log.info("登录操作，放行...");
            chain.doFilter(request,response);
            return;
        }
        //3.获取请求头名字
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录信息");
            Result error =  Result.error("NOT_LOGIN");
            //手动转换  对象-json ----->阿里巴巴工具包
            String notLogin = JSONObject.toJSONString(error);
            resq.getWriter().write(notLogin);
            return;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//出现异常解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error =  Result.error("NOT_LOGIN");
            //手动转换  对象-json ----->阿里巴巴工具包
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //6.放行
        log.info("令牌合法，放行");
        chain.doFilter(request,response);
    }
}
