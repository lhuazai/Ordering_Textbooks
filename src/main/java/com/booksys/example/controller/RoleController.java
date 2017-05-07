package com.booksys.example.controller;

import com.booksys.example.bean.ServiceRes;
import com.booksys.example.model.*;
import com.booksys.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    RoleMenuRepository roleMenuRepository;

    @RequestMapping("/userRolePage")
    public String getUserRolePage(Model model){
        List<RoleEntity> roleEntities=roleRepository.findAll();
        model.addAttribute("roleList",roleEntities);
        List<MenuEntity> menuEntities=menuRepository.findAll();
        model.addAttribute("menuList",menuEntities);
        return "userRole";
    }

    @ResponseBody
    @RequestMapping("/ajax/RoleUser/{roleId}")
    public ServiceRes<HashMap> findUserRoleByRoleId(@PathVariable("roleId") Integer roleId){
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByRoleId(roleId);
        for (int i = 0; i <userroleEntities.size() ; i++) {
            RoleEntity roleEntity=roleRepository.findOne(userroleEntities.get(i).getRoleId());
            UserEntity userEntity=userRepository.findOne(userroleEntities.get(i).getUserId());
            userroleEntities.get(i).setRoleEntity(roleEntity);
            userroleEntities.get(i).setUserEntity(userEntity);
        }
        List<RolemenuEntity> rolemenuEntities=roleMenuRepository.findAllByRoleId(roleId);
        HashMap map=new HashMap();
        map.put("userRoleList",userroleEntities);
        map.put("roleMenuList",rolemenuEntities);
        return new ServiceRes<HashMap>(map);
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
            Boolean isExist=false;
            for (int j = 0; j <userroleEntities.size() ; j++) {
                if(userroleEntities.get(j).getUserId()==userIds[i]){
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

    @ResponseBody
    @RequestMapping(value = "/ajax/delUserRole/{userRoleId}")
    public ServiceRes addUserRole(@PathVariable("userRoleId") int userRoleId){
        userRoleRepository.delete(userRoleId);
        return new ServiceRes("刪除成功",true);
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/addMenu",method = RequestMethod.POST)
    public ServiceRes addUserRole(@RequestParam(name = "name",required = true) String name,@RequestParam(name = "url",required = true) String url){
        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setName(name);
        menuEntity.setUrl(url);
        menuRepository.saveAndFlush(menuEntity);
        return new ServiceRes("新增成功",true);
    }

    @RequestMapping(value = "/delMenu/{menuId}",method = RequestMethod.GET)
    public String delMenu(@PathVariable("menuId") int menuId){
        menuRepository.delete(menuId);
        return "redirect:/role/userRolePage";
    }
}
