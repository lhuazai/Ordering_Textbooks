package com.booksys.example.util;

import com.booksys.example.model.UserroleEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
public class RoleUtil {
    public static boolean isStudent(List<UserroleEntity> userroleEntities){
        for (int i = 0; i <userroleEntities.size(); i++) {
            if(userroleEntities.get(i).getRoleId()==3){
                return true;
            }
        }
        return false;
    }
    public static boolean isSuperAdmin(List<UserroleEntity> userroleEntities){
        for (int i = 0; i <userroleEntities.size(); i++) {
            if(userroleEntities.get(i).getRoleId()==1){
                return true;
            }
        }
        return false;
    }
    public static boolean isFacultyAdmin(List<UserroleEntity> userroleEntities){
        for (int i = 0; i <userroleEntities.size(); i++) {
            if(userroleEntities.get(i).getRoleId()==4){
                return true;
            }
        }
        return false;
    }
    public static boolean isTeacher(List<UserroleEntity> userroleEntities){
        for (int i = 0; i <userroleEntities.size(); i++) {
            if(userroleEntities.get(i).getRoleId()==2){
                return true;
            }
        }
        return false;
    }
    public static boolean isTeachAdmin(List<UserroleEntity> userroleEntities){
        for (int i = 0; i <userroleEntities.size(); i++) {
            if(userroleEntities.get(i).getRoleId()==5){
                return true;
            }
        }
        return false;
    }
}
