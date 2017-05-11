package com.booksys.example.controller;

import com.booksys.example.bean.ServiceRes;
import com.booksys.example.model.*;
import com.booksys.example.repository.ClazzRepository;
import com.booksys.example.repository.ClazzUserRepository;
import com.booksys.example.repository.UserRepository;
import com.booksys.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    ClazzRepository clazzRepository;
    @Autowired
    ClazzUserRepository clazzUserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    //班级管理
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String users(ModelMap modelMap){
        // 找到里面的所有记录
        List<ClazzEntity> clazzEntities=clazzRepository.findAll();
        for (int i = 0; i <clazzEntities.size() ; i++) {
            List<ClazzUserEntity> userEntities=clazzUserRepository.findAllByClazzId(clazzEntities.get(i).getId());
            clazzEntities.get(i).setCount(userEntities.size());
        }

        // 将所有的记录传递给返回的jsp页面
        modelMap.addAttribute("clazzList", clazzEntities);
        // 返回pages目录下的jsp
        return "clazzList";
    }

    // 新增班级
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServiceRes addBook(@RequestParam(name = "name",required = true) String name){
        ClazzEntity clazzEntity=new ClazzEntity();
        clazzEntity.setName(name);
        clazzEntity.setInUse(1);
        clazzRepository.saveAndFlush(clazzEntity);
        // 返回pages目录下的userManage.jsp
        return new ServiceRes("添加成功",true);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delClazz(@PathVariable("id") Integer id){
        clazzRepository.delete(id);
        return "redirect:/clazz/list";
    }
    @RequestMapping(value = "/user/del/{clazzId}/{id}", method = RequestMethod.GET)
    public String delClazzUser(@PathVariable("id") Integer id,@PathVariable("clazzId") Integer clazzId){
        clazzUserRepository.delete(id);
        return "redirect:/clazz/users/"+clazzId;
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String clazzUsers(@PathVariable("id") Integer id,ModelMap modelMap){
        ClazzEntity clazzEntity=clazzRepository.findOne(id);
        List<ClazzUserEntity> clazzUserEntities=clazzUserRepository.findAllByClazzId(id);
        clazzEntity.setCount(clazzUserEntities.size());
        for (int i = 0; i <clazzUserEntities.size() ; i++) {
            UserEntity userEntity=userRepository.findOne(clazzUserEntities.get(i).getUserId());
            clazzUserEntities.get(i).setUserEntity(userEntity);
        }
        modelMap.addAttribute("clazz",clazzEntity);
        modelMap.addAttribute("userList",clazzUserEntities);
        return "clazzUsers";
    }


    @ResponseBody
    @RequestMapping(value = "/ajax/addUser",method = RequestMethod.POST)
    public ServiceRes addUser(@RequestParam(name = "userIds",required = true) int[] userIds,@RequestParam(name = "clazzId",required = true) int clazzId){
        for (int i = 0; i <userIds.length ; i++) {
            ClazzUserEntity clazzUserEntity=new ClazzUserEntity();
            clazzUserEntity.setUserId(userIds[i]);
            clazzUserEntity.setClazzId(clazzId);
            clazzUserRepository.save(clazzUserEntity);
        }
        clazzUserRepository.flush();

        return new ServiceRes("添加成功",true);
    }
}
