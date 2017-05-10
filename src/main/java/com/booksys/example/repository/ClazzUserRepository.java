package com.booksys.example.repository;

import com.booksys.example.model.ClazzEntity;
import com.booksys.example.model.ClazzUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface ClazzUserRepository extends JpaRepository<ClazzUserEntity,Integer> {
    List<ClazzUserEntity> findAllByClazzId(int clazzId);
}
