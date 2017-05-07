package com.booksys.example.repository;

import com.booksys.example.model.RolemenuEntity;
import com.booksys.example.model.UserroleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface RoleMenuRepository extends JpaRepository<RolemenuEntity,Integer> {
    public List<RolemenuEntity> findAllByRoleId(int roleId);
}
