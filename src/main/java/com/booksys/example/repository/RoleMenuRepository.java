package com.booksys.example.repository;

import com.booksys.example.model.RolemenuEntity;
import com.booksys.example.model.UserroleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface RoleMenuRepository extends JpaRepository<RolemenuEntity,Integer> {
    List<RolemenuEntity> findAllByRoleId(int roleId);
    List<RolemenuEntity> findAllByRoleIdAndMenuId(int roleId,int menuId);
    List<RolemenuEntity> findDistinctByRoleIdIn(List<Integer> roleIds);
    @Modifying
    @Transactional
    @Query("delete from RolemenuEntity m where m.roleId=:qroleId and m.menuId=:qmenuId")
    void deleteAllByRoleIdAndMenuId(@Param("qroleId") int qroleId, @Param("qmenuId") int qmenuId);
}
