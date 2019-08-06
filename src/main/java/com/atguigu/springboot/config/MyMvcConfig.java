package com.atguigu.springboot.config;


import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocalResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.awt.*;
import java.beans.Customizer;
import java.net.InetAddress;
import java.util.Set;

//使用WebMvcConfigurerAdapter可以扩展SpringMVc的功能
//全面接管springMvc，使得SpringMvc的自动配置都失效，静态页面不能被访问
//@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/atguigu").setViewName("success");
    }


    //    所有的WebMvcConfigurationSupport组件都会一起起作用
    //将组件注册到容器中
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            /*注册拦截器*/
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               /* registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                 .excludePathPatterns("/login.html","/","/user/login");*/
        }

        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
    /*public class MyMvcConfig extends WebMvcConfigurationSupport {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        访问连接atguigu跳转到success页面
        registry.addViewController("atguigu").setViewName("success");
    }


//    所有的WebMvcConfigurationSupport组件都会一起起作用
    //将组件注册到容器中
   *//* @Bean
    public WebMvcConfigurationSupport webMvcConfigurationSupport() {
        WebMvcConfigurationSupport support = new WebMvcConfigurationSupport() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("login").setViewName("login");
            }
            };
                    return support;
    }*/
}
