package com.ksa.fullstack.comment.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc  //<annotation-driven /> 
@EnableAspectJAutoProxy //<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
@ComponentScan(basePackages = {"com.ksa.fullstack.comment"} )
@MapperScan(basePackages ="com.ksa.fullstack.comment.dao")
public class CommentServletConfig {

}
