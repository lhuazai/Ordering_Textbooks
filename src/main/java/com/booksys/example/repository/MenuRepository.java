package com.booksys.example.repository;

import com.booksys.example.model.MenuEntity;
import com.booksys.example.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface MenuRepository extends JpaRepository<MenuEntity,Integer> {

}
