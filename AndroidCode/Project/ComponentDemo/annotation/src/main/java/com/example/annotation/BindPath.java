package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 元注解
@Target(ElementType.TYPE) // 声明注解处理器的作用域
@Retention(RetentionPolicy.CLASS) // 声明注解的存在周期 生命周期：源码期 编译期 运行期
public @interface BindPath {
    String value();
}
