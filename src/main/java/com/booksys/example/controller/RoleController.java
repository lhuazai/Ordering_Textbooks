package com.booksys.example.controller;

import com.booksys.example.bean.ServiceRes;
import com.booksys.example.model.RoleEntity;
import com.booksys.example.model.UserEntity;
import com.booksys.example.model.UserroleEntity;
import com.booksys.example.repository.RoleRepository;
import com.booksys.example.repository.UserRepository;
import com.booksys.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/userRolePage")
    public String getUserRolePage(Model model){
        List<RoleEntity> roleEntities=roleRepository.findAll();
        model.addAttribute("roleList",roleEntities);
        return "userRole";
    }

    @ResponseBody
    @RequestMapping("/ajax/RoleUser/{roleId}")
    public ServiceRes<List<UserroleEntity>> findUserRoleByRoleId(@PathVariable("roleId") Integer roleId){
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByRoleId(roleId);
        for (int i = 0; i <userroleEntities.size() ; i++) {
            RoleEntity roleEntity=roleRepository.findOne(userroleEntities.get(i).getRoleId());
            UserEntity userEntity=userRepository.findOne(userroleEntities.get(i).getUserId());
            userroleEntities.get(i).setRoleEntity(roleEntity);
            userroleEntities.get(i).setUserEntity(userEntity);
        }

        return new ServiceRes<List<UserroleEntity>>(userroleEntities);
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/addRole",method = RequestMethod.POST)
    public ServiceRes addRole(@ModelAttribute("role") RoleEntity roleEntity){
        roleRepository.saveAndFlush(roleEntity);
        return new ServiceRes("新增角色成功",true);
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/delRole/{roleId}")
    public ServiceRes delRole(@PathVariable("roleId") Integer roleId){
        roleRepository.delete(roleId);
        return new ServiceRes("删除角色成功",true);
    }
}
