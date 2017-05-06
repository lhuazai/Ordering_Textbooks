package com.booksys.example.repository;

import com.booksys.example.model.UserroleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserroleEntity,Integer> {
    List<UserroleEntity> findAllByUserId(int userId);
    List<UserroleEntity> findAllByRoleId(int roleId);
}
