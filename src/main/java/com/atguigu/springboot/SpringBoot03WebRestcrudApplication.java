package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class SpringBoot03WebRestcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot03WebRestcrudApplication.class, args);
    }

//    自己定义一个类实现视图解析器，然后自己使用的话就可以将其视图解析器组件放到容器中里面即可
    @Bean
    public MyViewResolver myViewResolver(){
        return new MyViewResolver();
    }



    private static class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

}
