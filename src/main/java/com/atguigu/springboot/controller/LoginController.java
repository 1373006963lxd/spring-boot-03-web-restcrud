package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

   /* @DeleteMapping
    @PutMapping
    @GetMapping
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)*/

   /*处理请求*/
    @PostMapping(value = "/user/login")
    public  String login(@RequestParam("emailaddress") String emailaddress, @RequestParam("password") String password,
                         Map<String,Object> map , HttpSession session){
        if(!StringUtils.isEmpty(emailaddress)&&"123456".equals(password)){
            //为了用户登录成功以后，就可以保留用户信息
            session.setAttribute("loginEmailAddress",emailaddress);
            //为了防止表单重复提交，砸门咱们使用重定向
            return "redirect:/main.html";
        }else{
            map.put("message","输入的密码错误");
            return "login";
        }
    }
}
