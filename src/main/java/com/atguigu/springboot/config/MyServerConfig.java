package com.atguigu.springboot.config;

import com.atguigu.springboot.filter.MyFilter;
import com.atguigu.springboot.listener.MyListener;
import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

@Configuration
public class MyServerConfig {


    //注册三大组件

    /*加到容器中*/
    @Bean
    public ServletRegistrationBean myServlet(){
        //当访问/myServlet 时候就会 调到new MyServlet()里面
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        return registrationBean;
    }
/*
*
* 过滤
* */

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    /*
    监听
    * */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean(new MyListener());
        return registrationBean;
    }

    //配置嵌入式的Servlet容器 这里到一个接口

}
