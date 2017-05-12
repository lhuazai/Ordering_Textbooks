package com.booksys.example.controller;

import com.booksys.example.model.MenuEntity;
import com.booksys.example.model.RolemenuEntity;
import com.booksys.example.model.UserEntity;
import com.booksys.example.model.UserroleEntity;
import com.booksys.example.repository.MenuRepository;
import com.booksys.example.repository.RoleMenuRepository;
import com.booksys.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booksys.example.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjj on 2015/10/24 0024.
 */
@Controller
public class MainController {

    // 自动装配
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleMenuRepository roleMenuRepository;
    @Autowired
    private MenuRepository menuRepository;

    // 首页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap modelMap) {
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByUserId(userEntity.getId());
        List<Integer> roleIds=new ArrayList<>();
        for (int i = 0; i <userroleEntities.size() ; i++) {
            roleIds.add(userroleEntities.get(i).getRoleId());
        }
        List<RolemenuEntity> rolemenuEntities=roleMenuRepository.findDistinctByRoleIdIn(roleIds);
        List<Integer> menuIds=new ArrayList<>();
        for (int i = 0; i <rolemenuEntities.size() ; i++) {
            menuIds.add(rolemenuEntities.get(i).getMenuId());
        }
        List<MenuEntity> menuEntities=menuRepository.findDistinctByIdIn(menuIds);
        modelMap.addAttribute("menus",menuEntities);
        modelMap.addAttribute("user",userEntity);
        return "index";
    }


}