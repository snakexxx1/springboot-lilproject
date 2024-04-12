package com.snake.springbootlilproject.aop;

import com.alibaba.fastjson.JSONObject;
import com.snake.springbootlilproject.mapper.OperateLogMapper;
import com.snake.springbootlilproject.pojo.OperateLog;
import com.snake.springbootlilproject.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.snake.springbootlilproject.anno.Log)")
    public Object recorfLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID-当前登录员工ID
          //获取请求头中的JWT令牌，并解析
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser =(Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类类名
        String classname = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();


        long end =System.currentTimeMillis();
        //方法执行返回值
        String returnValus = JSONObject.toJSONString(result);
        //操作耗时
        long costTime =end - begin;
        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,classname,methName,methodParams,returnValus,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志：{}",operateLog);


        return result;

    }
}
