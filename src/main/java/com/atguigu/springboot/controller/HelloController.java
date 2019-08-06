package com.atguigu.springboot.controller;


import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {


    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            /*这里抛出自定义的异常*/
            throw new UserNotExistException();
        }
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi"));
        return "success";
    }

//    直接访问"/","index.html"都会跳转到templates里面的sucess页面。
/*    @RequestMapping({"/","index.html"})
    public String success(){
        return "index";
    }*/
}
