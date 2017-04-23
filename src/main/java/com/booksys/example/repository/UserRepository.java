package com.booksys.example.repository;

import com.booksys.example.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sjj on 2015/10/24 0024.
 */
@Repository // 添加注解
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Modifying // 说明该方法是修改操作
    @Transactional // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.name=:qName, us.userName=:qUserName, us.password=:qPassword,us.email=:qemail,us.sex=:qsex,us.qq=:qqq where us.id=:qId")
    void updateUser(@Param("qName") String qName,
                    @Param("qUserName") String qUserName,
                    @Param("qPassword") String password,
                    @Param("qId") Integer id,
                    @Param("qemail") String email,
                    @Param("qsex") String sex,
                    @Param("qqq") String qq
                    );

    UserEntity findByUserName(String userName);
}