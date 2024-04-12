package com.snake.springbootlilproject.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target(ElementType.METHOD)//作用在方法上
public @interface Log {
}
