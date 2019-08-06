package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*为了使localhost:8080/crud/hello?user=aaa 抛出自定的异常json形式
* 如果不自定义就会抛出默认的设置json数据
* 浏览器的话就会默认抛出在静态资源里面的5xx信息
* */
/*异常处理器的注解*/
@ControllerAdvice
public class MyExceptionHandler {


    /*这样的结果就是没有实现自适应的效果
    *
    * 第一种 1. 浏览器客户端返回的都是json数据  -------springboot中有BasicErrorController有实现
    * */
   /* @ResponseBody
    //这个注解可以是处理任何异常  （Exception）
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){

        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notException");
        map.put("message",e.getMessage());
        return map;
    }*/

    /*2. 实现自适应效果*/
    //这个注解可以是处理任何异常  （Exception）
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e,HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码，不然的话会显示默认的200
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notException");
        map.put("message","用户出错啦");

        request.setAttribute("ext",map);
        //转发到error 进行自适应处理
        return "forward:/error";
    }

}
