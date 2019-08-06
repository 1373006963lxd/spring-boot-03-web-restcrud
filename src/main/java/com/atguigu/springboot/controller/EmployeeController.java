package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public  String list(Model model){
      Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf 默认就会拼串
        //classpath:/templates/xxx.html  return "emp/list";
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        /*直接departmentDao.getDepartments(); 然后alter+ enter制动命名*/
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //来到添加页面
        return "emp/add";
    }

    //员工添加
    @PostMapping("/emp")
    //SpringMvc 自动将请求参数和入参对象的属性进行---绑定；要求就是请求参数的名字和javaBean入参的对象里面的属性名一致
    public String addEmp(Employee employee){

        employeeDao.save(employee);
        //来到员工列表页面
        //redirect: 表示重定向到一个地址    /代表当前项目路径  ----- 这里可以看thymeleafViewResolver源码
        //forward： 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面（add是一个修改添加 合二为一的页面）
        return "/emp/add";
    }
    /*员工修改*/

    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}


