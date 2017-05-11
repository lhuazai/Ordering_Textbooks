package com.booksys.example.controller;

import com.booksys.example.bean.ServiceRes;
import com.booksys.example.model.UserEntity;
import com.booksys.example.model.UserroleEntity;
import com.booksys.example.repository.UserRepository;
import com.booksys.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
@Controller
public class UserController {
    // 自动装配
    @Autowired
    private UserRepository userRepository;


    // 用户管理
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(ModelMap modelMap){
        // 找到user表里面的所有记录
        List<UserEntity> userEntityList = userRepository.findAll();

        // 将所有的记录传递给返回的jsp页面
        modelMap.addAttribute("userList", userEntityList);

        // 返回pages目录下的userManage.jsp
        return "userManage";
    }
    // 用户信息ajax
    @ResponseBody
    @RequestMapping(value = "/ajax/users", method = RequestMethod.GET)
    public ServiceRes<List<UserEntity>> ajaxUsers(ModelMap modelMap){
        // 找到user表里面的所有记录
        List<UserEntity> userEntityList = userRepository.findAll();
        // 返回pages目录下的userManage.jsp
        return new ServiceRes<>(userEntityList);
    }
    // 添加用户表单页面
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(){
        return "addUser";
    }

    // 添加用户处理
    @RequestMapping(value = "/addUserPost", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity){
        // 向数据库添加一个用户
        //userRepository.save(userEntity);
        userEntity.setSignupTime(new Date());
        // 向数据库添加一个用户，并将内存中缓存区的数据刷新，立即写入数据库，之后才可以进行访问读取
        userRepository.saveAndFlush(userEntity);

        // 返回重定向页面
        return "redirect:/users";
    }

    // 查看用户详细信息
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/showUser/1 ，将匹配 userId = 1
    @RequestMapping(value = "/showUser/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable("userId") Integer userId, ModelMap modelMap ){
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user", userEntity);
        return "userDetail";
    }

    // 更新用户信息页面
    @RequestMapping(value = "/updateUser/{Id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("Id") Integer Id, ModelMap modelMap){
        UserEntity userEntity = userRepository.findOne(Id);
        modelMap.addAttribute("user", userEntity);
        return "updateUser";
    }
    // 处理用户修改请求
    @RequestMapping(value = "/updateUserPost", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("user") UserEntity userEntity){
        userRepository.updateUser(
                userEntity.getName(),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getSex(),
                userEntity.getQq()
        );
        return "redirect:/users";
    }

    // 删除用户
    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") Integer userId){
        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新数据库
        userRepository.flush();
        return "redirect:/users";
    }

    //登陆页面
    @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
    public String userLogin(){
        return "userLogin";
    }

    //登陆验证
    @RequestMapping(value = "/userLoginPost",method = RequestMethod.POST)
    public String userLoginPost(@ModelAttribute("user") UserEntity userEntity, ModelMap modelMap, HttpSession session){
        UserEntity userEntityBd = userRepository.findByUserName(userEntity.getUserName());
        String message;
        if (userEntityBd==null){
            message="用户不存在";
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("user", userEntity);
            return "userLogin";
        }
        if(userEntity.getPassword()==null||(!userEntity.getPassword().equals(userEntityBd.getPassword()))){
            message="密码错误";
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("user", userEntity);
            return "userLogin";
        }
        session.setAttribute("user",userEntityBd);
        return "redirect:/";
    }

    //登出行为
    @RequestMapping(value = "/userLoginout",method = RequestMethod.GET)
    public String userLoginout(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/userLogin";
    }


}
