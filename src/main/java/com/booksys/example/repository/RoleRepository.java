package com.booksys.example.repository;

import com.booksys.example.model.RoleEntity;
import com.booksys.example.model.UserroleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

}
