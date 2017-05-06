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
import java.util.ArrayList;
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
        try {
            roleRepository.saveAndFlush(roleEntity);
        }catch (Exception e){
            e.printStackTrace();
            return new ServiceRes("新增角色失败",false);
        }

        return new ServiceRes("新增角色成功",true);
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/delRole/{roleId}")
    public ServiceRes delRole(@PathVariable("roleId") Integer roleId){
        roleRepository.delete(roleId);
        return new ServiceRes("删除角色成功",true);
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/addUserRole",method = RequestMethod.POST)
    public ServiceRes addUserRole(@RequestParam(name = "roleId",required = true) Integer roleId,@RequestParam(name = "userIds",required = true) int[] userIds){
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByRoleId(roleId);
        ArrayList<Integer> userIdsNeed=new ArrayList<>();
        for (int i = 0; i <userIds.length ; i++) {
            boolean isExist=false;
            for (int j = 0; j <userroleEntities.size() ; j++) {
                if(userroleEntities.get(j).getRoleId()==userIds[i]){
                    isExist=true;
                }
            }
            if(!isExist){
                userIdsNeed.add(userIds[i]);
            }
        }
        try {
            for (int i = 0; i <userIdsNeed.size(); i++) {
                UserroleEntity userroleEntity=new UserroleEntity();
                userroleEntity.setRoleId(roleId);
                userroleEntity.setUserId(userIdsNeed.get(i));
                userRoleRepository.saveAndFlush(userroleEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ServiceRes("设置失败",false);
        }


        return  new ServiceRes("设置成功",true);
    }
}
